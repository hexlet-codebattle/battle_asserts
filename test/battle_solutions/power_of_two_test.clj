(ns battle-solutions.power-of-two-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-power-of-two [n]
  (and (not (zero? n))
       (zero? (bit-and n (dec n)))))

(deftest test-asserts
  (assert-true (is-power-of-two 16))
  (assert-true (not (is-power-of-two 20)))
  (assert-true (is-power-of-two 1))
  (assert-true (not (is-power-of-two 258)))
  (assert-true (is-power-of-two 512))
  (assert-true (not (is-power-of-two 513)))
  (assert-true (is-power-of-two 1024))
  (assert-true (not (is-power-of-two 0))))
