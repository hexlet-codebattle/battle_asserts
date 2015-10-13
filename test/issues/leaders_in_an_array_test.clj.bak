(ns battle-solutions.leaders-in-an-array-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn first-is-biggest [[head & tail]]
  (every? #(> head %) tail))

(defn leaders [array]
  (->>
   array
   (map-indexed vector)
   (filter
    #(first-is-biggest (subvec array (first %))))
   (map second)))

(deftest test-asserts
  (assert-equal [17 5 2] (leaders [16 17 4 3 5 2]))
  (assert-equal [67 45 35 8] (leaders [4 3 7 12 6 67 5 45 34 35 2 8]))
  (assert-equal [12 8 7 6] (leaders [12 10 12 8 7 6]))
  (assert-equal [5 4] (leaders [1 2 3 4 5 4])))
