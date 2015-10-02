(ns battle-solutions.factorial-trailing-zeroes-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn factorial [n]
  (apply *' (range 1 (+ n 1))))
(defn trailing-zeroes [n]
  (->>
   n
   factorial
   str
   (re-find #"0+$")
   count))

(deftest test-asserts
  (assert-equal 0 (trailing-zeroes 0))
  (assert-equal 1 (trailing-zeroes 5))
  (assert-equal 1 (trailing-zeroes 7))
  (assert-equal 4 (trailing-zeroes 23)))
