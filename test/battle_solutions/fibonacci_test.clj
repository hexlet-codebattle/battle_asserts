(ns battle-solutions.fibonacci-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn fibo-finder [number]
  (condp = number
    0 0
    1 1
    (+
      (fibo-finder (- number 1))
      (fibo-finder (- number 2)))))

(deftest test-asserts
  (assert-equal 0 (fibo-finder 0))
  (assert-equal 1 (fibo-finder 1))
  (assert-equal 3 (fibo-finder 4))
  (assert-equal 13 (fibo-finder 7))
  (assert-equal 55 (fibo-finder 10)))

