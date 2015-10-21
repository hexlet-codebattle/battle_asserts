(ns battle-asserts.issues.key-for-min-value-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.key-for-min-value :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)]
                  (or (nil? result)
                      (= (get (first v) result)
                         (apply min (vals (first v))))))))

(h/generate-tests issue/test-data issue/solution)
