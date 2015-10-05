(ns battle-solutions.single-number-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn single-number [array]
  (apply bit-xor array))

(deftest test-asserts
  (assert-equal 3 (single-number [2 2 3]))
  (assert-equal 4 (single-number [4 2 2 3 6 3 8 7 8 6 7]))
  (assert-equal 5 (single-number [1 3 4 3 5 6 7 1 6 7 4]))
  (assert-equal 6 (single-number [8 6 8 7 2 2 7 1 9 9 1])))
