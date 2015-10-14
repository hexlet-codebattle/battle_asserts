(ns battle-asserts.issues.disjoint-sets-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.disjoint-sets :as issue]))

(deftest test-asserts
  (is (issue/solution [12 34 11 9 3] [7 2 1 5]))
  (is (not (issue/solution [12 34 11 9 3] [2 1 3 5])))
  (is (issue/solution [15 16 7 2 1] [14 20 8 6 0]))
  (is (not (issue/solution [1 2 4 5 8 9] [2 1 3 5 9]))))
