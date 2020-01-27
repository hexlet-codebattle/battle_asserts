(ns battle-asserts.core
  (:require [clj-yaml.core :as yaml]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [clojure.tools.namespace.find :as nsf]))

(defn- generate-asserts
  [generator solution]
  (let [coll (gen/sample generator 20)]
    (map #(hash-map :expected (apply solution %) :arguments %)
         coll)))

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
        generator ((ns-resolve issue-ns-name 'arguments-generator))
        solution (ns-resolve issue-ns-name 'solution)
        disabled (ns-resolve issue-ns-name 'disabled)
        signature (ns-resolve issue-ns-name 'signature)
        description @(ns-resolve issue-ns-name 'description)
        samples @(ns-resolve issue-ns-name 'test-data)]
    (let [filename (str "issues/" issue-name ".yml")
          metadata {:level @(ns-resolve issue-ns-name 'level)
                    :disabled (if (nil? disabled) false @signature)
                    :signature (if (nil? signature) {} @signature)
                    :description (render-description description samples)}
          yaml (yaml/generate-string metadata :dumper-options {:flow-style :block})] (spit filename yaml))

    (let [filename (str "issues/" issue-name ".jsons")
          asserts (generate-asserts generator solution)]
      (with-open [w (io/writer filename)]
        (doall (map #(.write w (str (json/write-str %) "\n"))
                    asserts))))))

(defn -main [& args]
  (let [namespaces (-> "src/battle_asserts/issues"
                       clojure.java.io/as-file
                       nsf/find-namespaces-in-dir)]
    (doall (map generate-issues namespaces))))
