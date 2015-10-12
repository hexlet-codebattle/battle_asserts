(ns battle-asserts.core
  (:require [battle-asserts.checker :as checker]
            [clojure.string :as s]
            [clj-yaml.core :as yaml]
            [clojure.pprint :as pp]
            [clojure.test.check.generators :as gen]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.tools.namespace.find :as nsf]))

(defn- generate-asserts
  [generator solution]
  (let [coll (gen/sample generator 20)]
    (map #(hash-map :expected (apply solution %) :arguments %)
         coll)))

(defn generate-issues
  [issue-ns-name]
  (require [issue-ns-name])
  (let [issue-name (last (s/split (str issue-ns-name) #"\."))]

    (let [filename (str "issues/" issue-name ".yml")
          metadata {:level @(ns-resolve issue-ns-name 'level)
                    :description (str @(ns-resolve issue-ns-name 'description))}
          yaml (yaml/generate-string metadata :dumper-options {:flow-style :block})
          result-file filename]
      (spit result-file yaml))

    (let [filename (str "issues/" issue-name ".jsons")
          generator ((ns-resolve issue-ns-name 'arguments-generator))
          solution (ns-resolve issue-ns-name 'solution)
          asserts (generate-asserts generator solution)]
      (with-open [w (io/writer filename)]
        (doall (map #(.write w (str (json/write-str %) "\n"))
                    asserts))))))

(defn -main [& args]
  (let [namespaces (nsf/find-namespaces-in-dir (clojure.java.io/as-file "src/battle_asserts/issues"))]
    (doall (map generate-issues namespaces))))
