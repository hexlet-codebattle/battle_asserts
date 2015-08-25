(ns battle-solutions.difference-of-squares-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn square-of-sums
  [value]
  (let [sum (reduce + (range (inc value)))]
        (* sum sum)))

(defn sum-of-squares
  [value]
  (let [squares (map #(* % %) (range 1 (inc value)))]
        (reduce + squares)))

(defn difference
  [value]
  (- (square-of-sums value) (sum-of-squares value)))

(deftest test-asserts
  (assert-equal 170 (difference 5))
  (assert-equal 25164150 (difference 100))
  (assert-equal 100100 (difference 25))
  (assert-equal 0 (difference 1)))
