(ns battle-solutions.largest-pair-sum-in-array-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn largest-pair-sum [array]
  (apply + (take-last 2 (sort array))))

(deftest test-asserts
  (assert-equal 11 (largest-pair-sum [1 2 3 4 5 6]))
  (assert-equal 74 (largest-pair-sum [12 34 10 6 40]))
  (assert-equal 80 (largest-pair-sum [12 40 10 6 40]))
  (assert-equal 52 (largest-pair-sum [12 -34 10 6 40])))
