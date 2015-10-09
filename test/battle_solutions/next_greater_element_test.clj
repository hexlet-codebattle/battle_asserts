(ns battle-solutions.next-greater-element-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn max-than [value in]
  (first (filter #(< value %) in)))

(defn next-greater-elements [array]
  (map-indexed #(or
                 (max-than %2 (subvec array (inc %1)))
                 -1)
               array))

(deftest test-asserts
  (assert-equal [5 25 25 -1] (next-greater-elements [4 5 2 25]))
  (assert-equal [-1 12 12 -1] (next-greater-elements [13 7 6 12]))
  (assert-equal [6 8 12 5 5 12 -1 9 -1] (next-greater-elements [3 6 8 2 1 5 12 4 9]))
  (assert-equal [4 2 4 -1] (next-greater-elements [3 1 2 4])))
