(ns battle-solutions.credit-card-validator-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn- get-digit
  [digit]
  (let [digit-double (* digit 2)]
    (if (> digit-double  9)
      (- digit-double 9)
      digit-double)))

(defn- change-digits
  [arr]
  (let [length (count arr)]
    (map-indexed
     (fn [index digit]
       (if (or
            (and (even? index) (even? length))
            (and (odd? index) (odd? length)))
         (get-digit digit)
         digit))
     arr)))

(defn is-valid-credit-card
  [number]
  (let [arr-number-card (map #(read-string (str %)) number)
        sum (reduce + (change-digits arr-number-card))]
    (zero? (mod sum 10))))

(deftest test-asserts
  (assert-true (not (is-valid-credit-card "1234567890123456")))
  (assert-true (is-valid-credit-card "4408041234567893"))
  (assert-true (not (is-valid-credit-card "4408042234567893")))
  (assert-true (is-valid-credit-card "38520000023237"))
  (assert-true (is-valid-credit-card "4222222222222")))
