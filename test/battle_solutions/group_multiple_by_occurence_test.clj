(ns battle-solutions.group-multiple-by-occurence-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn group-multiple [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (reduce #(concat %1 (repeat (last %2) (first %2))) [])))

(deftest test-asserts
  (assert-equal [5 5 3 3 3 1] (group-multiple [5 3 5 1 3 3]))
  (assert-equal [4 4 4 6 6 9 9 2 3 10] (group-multiple [4 6 9 2 3 4 9 6 10 4]))
  (assert-equal [10 10 10 5 3 3 4 1] (group-multiple [10 5 3 10 10 4 1 3])))
