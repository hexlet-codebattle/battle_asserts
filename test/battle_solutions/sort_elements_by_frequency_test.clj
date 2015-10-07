(ns battle-solutions.sort-elements-by-frequency-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn sort-by-frequency [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (sort-by second #(compare %2 %1))
   (reduce #(concat %1 (repeat (second %2) (first %2))) [])))

(deftest test-asserts
  (assert-equal [2 2 2 3 4] (sort-by-frequency [2 2 2 3 4]))
  (assert-equal [3 3 3 3 2 2 2 12 12 4 5] (sort-by-frequency [2 3 2 4 5 12 2 3 3 3 12]))
  (assert-equal [1 1 1 1 2 2 3 3 4] (sort-by-frequency [1 2 1 2 1 4 3 3 1]))
  (assert-equal [8 8 7 7 2 2 1 1 9 9 6] (sort-by-frequency [8 6 8 7 2 2 7 1 9 9 1])))
