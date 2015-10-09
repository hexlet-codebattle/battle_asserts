(ns battle-solutions.fixed-point-in-array-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn fixed-point [array]
  (or
   (->>
    array
    (map-indexed vector)
    (filter #(= (first %) (second %)))
    ffirst)
   -1))

(deftest test-asserts
  (assert-equal 3, (fixed-point [-10, -5, 0, 3, 7]))
  (assert-equal 0, (fixed-point [0, 2, 5, 8, 17]))
  (assert-equal -1, (fixed-point [-10, -5, 3, 4, 7, 9]))
  (assert-equal 3, (fixed-point [-3 -2 -1 3 4 7 8])))
