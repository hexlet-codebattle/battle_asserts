(ns battle-solutions.sum-of-exp-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn sum-of-exp
  [number]
  (reduce + (take number (iterate #(+ (* 10 %) number) number)))
  )

(deftest test-asserts
  (assert-equal 369 (sum-of-exp 3))
  (assert-equal 61725 (sum-of-exp 5))
  (assert-equal 740736 (sum-of-exp 6))
  (assert-equal 98765424 (sum-of-exp 8)))
