(ns battle-asserts.core-test
  (:require [test-helper :as h]
            [clojure.string :as s]
            [clojure.test :refer :all]
            [clojure.tools.namespace.find :as nsf]))

(defn test-issue [issue-ns-name]
  (require [issue-ns-name])
  (let [issue-name (s/replace (last (s/split (str issue-ns-name) #"\."))
                              #"-"
                              "_")
        build-generator (ns-resolve issue-ns-name 'arguments-generator)
        solution (ns-resolve issue-ns-name 'solution)
        disabled (ns-resolve issue-ns-name 'disabled)
        signature (ns-resolve issue-ns-name 'signature)
        samples @(ns-resolve issue-ns-name 'test-data)]
    (when-not disabled
      (println (str "Running test for " issue-name))
      (h/run-test-data-spec-test samples signature issue-name)
      (when-not (nil? solution) (h/run-solution-test samples solution issue-name))
      (when-not (nil? build-generator) (h/run-generator-spec-test build-generator signature))
      (when-not (and (nil? build-generator) (nil? solution)) (h/run-solution-spec-test build-generator signature solution)))))

(deftest core-test
  (let [namespaces (-> "src/battle_asserts/issues"
                       clojure.java.io/as-file
                       nsf/find-namespaces-in-dir)]
    (doseq [namespace namespaces]
      (test-issue namespace))))