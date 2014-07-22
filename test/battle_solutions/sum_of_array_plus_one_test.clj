(ns battle-solutions.sum-of-array-plus-one-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn plus-one-sum [arr]
  (reduce #(+ %1 (+ %2 1)) 0 arr))

(deftest test-asserts
  (assert-equal 14 (plus-one-sum [1 2 3 4]))
  (assert-equal 16 (plus-one-sum [12 -3 0 3])))
