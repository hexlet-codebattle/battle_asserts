(ns battle-solutions.power-digits-sum-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn power-digits-sum [n]
  (reduce + (map #(Character/digit % 10) (str (apply * (repeat n 2))))))

(deftest test-asserts
    (assert-equal 13 (power-digits-sum 8))
    (assert-equal 26 (power-digits-sum 15)))
