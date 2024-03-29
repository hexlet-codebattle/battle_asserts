(ns battle-asserts.core-test
  (:require [test-helper :as h]
            [clojure.string :as s]
            [clojure.test :refer [deftest]]
            [clojure.java.io :as io]
            [clojure.tools.namespace.find :as nsf]))

(defn test-issue [issue-ns-name]
  (require [issue-ns-name])
  (let [issue-name (s/replace (last (s/split (str issue-ns-name) #"\."))
                              #"-"
                              "_")
        build-generator (ns-resolve issue-ns-name 'arguments-generator)
        solution (ns-resolve issue-ns-name 'solution)
        difficulty @(ns-resolve issue-ns-name 'level)
        disabled (ns-resolve issue-ns-name 'disabled)
        signature (ns-resolve issue-ns-name 'signature)
        description @(ns-resolve issue-ns-name 'description)
        samples @(ns-resolve issue-ns-name 'test-data)]
    (when-not disabled
      (h/run-test-data-spec-test samples signature issue-name)
      (h/run-description-test description issue-name)
      (h/run-difficulty-test difficulty issue-name)
      (when-not (nil? solution) (h/run-solution-test samples solution issue-name))
      (when-not (nil? build-generator) (h/run-generator-spec-test build-generator signature issue-name))
      (when-not (and
                 (nil? build-generator)
                 (nil? solution)) (h/run-solution-spec-test
                                   build-generator
                                   signature
                                   solution
                                   issue-name)))))

(deftest core-test
  (let [namespaces (-> "src/battle_asserts/issues"
                       io/as-file
                       nsf/find-namespaces-in-dir)]
    (doseq [namespace namespaces]
      (test-issue namespace))))
