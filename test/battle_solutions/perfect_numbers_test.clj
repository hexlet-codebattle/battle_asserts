(ns battle-solutions.perfect-numbers-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-perfect
  [num]
  (letfn [(divisors
            [num]
            (filter #(= (rem num %) 0)
                    (range 1 (inc (/ num 2)))))]
    (= num (reduce + (divisors num)))))

(deftest test-asserts
  (assert-true (is-perfect 6))
  (assert-true (not (is-perfect 7)))
  (assert-true (is-perfect 496))
  (assert-true (not (is-perfect 500)))
  (assert-true (is-perfect 8128)))
