(ns battle-solutions.count-smaller-elements-on-right-side-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn count-smaller [value array]
  (count (filter #(> value %) array)))

(defn smaller-elements-on-right [array]
  (map-indexed #(count-smaller %2 (subvec array (inc %1))) array))

(deftest test-asserts
  (assert-equal [6 1 1 1 0 1 0] (smaller-elements-on-right [12 1 2 3 0 11 4]))
  (assert-equal [4 3 2 1 0] (smaller-elements-on-right [5 4 3 2 1]))
  (assert-equal [0 0 0 0 0] (smaller-elements-on-right [1 2 3 4 5]))
  (assert-equal [6 5 7 4 1 3 0 2 0 0] (smaller-elements-on-right [7 6 10 5 2 8 1 9 3 4])))
