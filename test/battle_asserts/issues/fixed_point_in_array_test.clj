(ns battle-asserts.issues.fixed-point-in-array-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.fixed-point-in-array :as issue]))

(deftest test-solution
  (is (= 3 (issue/solution [-10 -5 0 3 7])))
  (is (= 0 (issue/solution [0 2 5 8 17])))
  (is (= -1 (issue/solution [-10 -5 3 4 7 9])))
  (is (= 3 (issue/solution [-3 -2 -1 3 4 7 8]))))
