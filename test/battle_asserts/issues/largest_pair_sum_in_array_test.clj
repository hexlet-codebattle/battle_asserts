(ns battle-asserts.issues.largest-pair-sum-in-array-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.largest-pair-sum-in-array :as issue]))

(deftest test-solution
  (is (= 11 (issue/solution [1 2 3 4 5 6])))
  (is (= 74 (issue/solution [12 34 10 6 40])))
  (is (= 80 (issue/solution [12 40 10 6 40])))
  (is (= 52 (issue/solution [12 -34 10 6 40]))))
