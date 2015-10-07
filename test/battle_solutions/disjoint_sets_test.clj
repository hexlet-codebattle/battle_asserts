(ns battle-solutions.disjoint-sets-test
  (:require [clojure.test :refer :all]
            [clojure.set :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn are-disjoint  [first-set second-set]
  (empty? (intersection (set first-set)
                        (set second-set))))

(deftest test-asserts
  (assert-true (are-disjoint [12 34 11 9 3] [7 2 1 5]))
  (assert-true (not (are-disjoint [12 34 11 9 3] [2 1 3 5])))
  (assert-true (are-disjoint [15 16 7 2 1] [14 20 8 6 0]))
  (assert-true (not (are-disjoint [1 2 4 5 8 9] [2 1 3 5 9]))))
