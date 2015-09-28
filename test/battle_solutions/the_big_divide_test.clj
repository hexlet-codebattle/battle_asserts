(ns battle-solutions.the-big-divide-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn the-big-divide
  [supr num1 num2]
  (reduce + (distinct (concat (range num1 supr num1)
                              (range num2 supr num2)))))

(deftest test-asserts
  (assert-equal 0 (the-big-divide 3 17 11))
  (assert-equal 23 (the-big-divide 10 3 5))
  (assert-equal 233168 (the-big-divide 1000 3 5)))

