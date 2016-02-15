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

(defn generate-issues
  [issue-ns-name]
  (require [issue-ns-name])
  (let [issue-name (s/replace (last (s/split (str issue-ns-name) #"\."))
                              #"-"
                              "_")
        generator ((ns-resolve issue-ns-name 'arguments-generator))
        solution (ns-resolve issue-ns-name 'solution)
        sample (first @(ns-resolve issue-ns-name 'test-data))]
    (let [filename (str "issues/" issue-name ".yml")
          json-options [:escape-unicode false :escape-slash false]
          arguments (s/join ", " (map #(apply json/write-str % json-options) (:arguments sample)))
          expected (apply json/write-str (:expected sample) json-options)
          description @(ns-resolve issue-ns-name 'description)
          metadata {:level @(ns-resolve issue-ns-name 'level)
                    :description (str description
                                      "\n\n"
                                      "Example: `" expected " == solution(" arguments ")`")}
          yaml (yaml/generate-string metadata :dumper-options {:flow-style :block})]
      (spit filename yaml))

    (let [filename (str "issues/" issue-name ".jsons")
          asserts (generate-asserts generator solution)]
      (with-open [w (io/writer filename)]
        (doall (map #(.write w (str (json/write-str %) "\n"))
                    asserts))))))

(defn -main [& args]
  (let [namespaces (nsf/find-namespaces-in-dir (clojure.java.io/as-file "src/battle_asserts/issues"))]
    (doall (map generate-issues namespaces))))
