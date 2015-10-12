(ns battle-solutions.fizzbuzz-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn fizzbuzz
  [number]
  (str
   (when (= (mod number 3) 0) "Fizz")
   (when (= (mod number 5) 0) "Buzz")))

(deftest test-asserts
  (assert-equal "Fizz" (fizzbuzz 3))
  (assert-equal "Buzz" (fizzbuzz 50))
  (assert-equal "FizzBuzz" (fizzbuzz 150))
  (assert-equal "FizzBuzz" (fizzbuzz 5175)))
