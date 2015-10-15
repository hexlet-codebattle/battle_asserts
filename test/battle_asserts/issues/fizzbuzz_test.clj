(ns battle-asserts.issues.fizzbuzz-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.fizzbuzz :as issue]))

(deftest test-solution
  (is (= "Fizz" (issue/solution 3)))
  (is (= "Buzz" (issue/solution 50)))
  (is (= "FizzBuzz" (issue/solution 150)))
  (is (= "FizzBuzz" (issue/solution 5175))))
