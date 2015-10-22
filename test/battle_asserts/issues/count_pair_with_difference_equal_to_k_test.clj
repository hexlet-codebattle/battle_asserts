(ns battle-asserts.issues.count-pair-with-difference-equal-to-k-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.count-pair-with-difference-equal-to-k :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (>= (apply issue/solution v)
                    0)))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
