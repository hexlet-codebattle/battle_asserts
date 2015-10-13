(ns battle-solutions.first-repeating-element-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn first-repeating-element [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (filter #(> (second %) 1))
   ffirst))

(deftest test-asserts
  (assert-equal 5, (first-repeating-element [10 5 3 4 3 5 6]))
  (assert-equal 6, (first-repeating-element [6 10 5 4 9 120 4 6 10]))
  (assert-equal 7, (first-repeating-element [6 10 7 4 9 120 4 7]))
  (assert-equal 8, (first-repeating-element [8 8 7 4 9 120 4 7])))
