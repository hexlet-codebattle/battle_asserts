(ns battle-solutions.perfect-numbers-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn perfect?
  [num]
  (letfn [(divisors
            [num]
            (filter #(= (rem num %) 0)
                    (range 1 (inc (/ num 2)))))]
    (= num (reduce + (divisors num)))))



(deftest test-asserts
  (assert-equal true (perfect? 6))
  (assert-equal false (perfect? 7))
  (assert-equal true (perfect? 496))
  (assert-equal false (perfect? 500))
  (assert-equal true (perfect? 8128)))
