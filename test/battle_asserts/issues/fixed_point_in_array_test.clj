(ns battle-asserts.issues.fixed-point-in-array-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.fixed-point-in-array :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [fixed-point (apply issue/solution v)]
                  (if (= fixed-point -1)
                    true
                    (= fixed-point
                       (nth (first v) fixed-point))))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
