(ns battle-asserts.core
  (:require [clj-yaml.core :as yaml]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as util]
            [clojure.tools.namespace.find :as nsf]))

(def max-asserts 20)

(defmulti generate-asserts
  (fn [build-generator solution _]
    (if (some nil? [build-generator solution])
      "samples"
      "solution")))

(defmethod generate-asserts "samples" [_ _ samples]
  samples)

(defmethod generate-asserts "solution" [build-generator solution samples]
  (let [generator (build-generator)
        size (count samples)
        coll (gen/sample generator (- max-asserts size))
        generated (map #(hash-map :expected (apply solution %) :arguments %)
                       coll)]
    (reduce
     (fn [acc task] (conj acc (into (sorted-map) task)))
     generated
     samples)))

(defn write-to-file [filename content-seq]
  (with-open [w (io/writer filename)]
    (doseq [content-hash content-seq]
      (.write w (str (json/write-str content-hash) "\n")))))

(defn render-description [description, samples]
  (let [json-options    [:escape-unicode false :escape-slash false]
        to-json         #(apply json/write-str % json-options)
        array-to-string #(s/join ", " (map to-json %))
        format-sample #(format
                        "%s  == solution(%s)"
                        (to-json (:expected %))
                        (array-to-string (:arguments %)))]
    (->>
     samples
     (mapv format-sample)
     (s/join "\n")
     (format "%s\n\n**Example:**\n```\n%s\n```" description))))

(defn generate-issues
  [issue-ns-name]
  (require [issue-ns-name])
  (let [issue-name (s/replace (last (s/split (str issue-ns-name) #"\."))
                              #"-"
                              "_")
        build-generator (ns-resolve issue-ns-name 'arguments-generator)
        solution (ns-resolve issue-ns-name 'solution)
        disabled (ns-resolve issue-ns-name 'disabled)
        signature (ns-resolve issue-ns-name 'signature)
        description @(ns-resolve issue-ns-name 'description)
        samples @(ns-resolve issue-ns-name 'test-data)]
    (let [filename (str "issues/" issue-name ".yml")
          metadata {:level @(ns-resolve issue-ns-name 'level)
                    :disabled (if (nil? disabled) false @disabled)
                    :signature (if (nil? signature) {} @signature)
                    :description (render-description description samples)}
          yaml (yaml/generate-string metadata :dumper-options {:flow-style :block})]  (spit filename yaml))
    (println (str "Proceeding " issue-name "..."))
    (let [filename (str "issues/" issue-name ".jsons")
          asserts (generate-asserts build-generator solution samples)]
      (if disabled
        (println (str issue-name " issue is disabled!"))
        (let [signature-errors (util/check-asserts-and-sign asserts @signature)]
          (if (empty? signature-errors)
            (do (write-to-file filename asserts) (println (str "Generated " issue-name " issue!")))
            (throw (Exception. (str "Errors in signature or arguments at " issue-name " issue!")))))))))

(defn -main [& args]
  (let [namespaces (-> "src/battle_asserts/issues"
                       clojure.java.io/as-file
                       nsf/find-namespaces-in-dir)]
    (doseq [namespace namespaces]
      (generate-issues namespace))))
