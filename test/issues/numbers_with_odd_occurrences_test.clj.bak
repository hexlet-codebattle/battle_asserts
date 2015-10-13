(ns battle-solutions.numbers-with-odd-occurrences-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn numbers-with-odd-occurence [array n]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (filter #(odd? (val %)))
   keys
   (take n)))

(deftest test-asserts
  (assert-equal [34 45] (numbers-with-odd-occurence [12 23 34 12 12 23 12 45] 2))
  (assert-equal [4 100 5000] (numbers-with-odd-occurence [4 4 100 5000 4 4 4 4 4 100 100] 3))
  (assert-equal [6 5 9 21] (numbers-with-odd-occurence [3 3 4 6 4 5 9 9 21 9] 4))
  (assert-equal [8 16 23 42] (numbers-with-odd-occurence [4 8 15 16 23 42 4 15 42 42] 4)))
