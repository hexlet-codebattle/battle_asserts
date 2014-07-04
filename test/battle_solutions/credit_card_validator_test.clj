(ns battle-solutions.credit-card-validator-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert]]))

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

(defn valid-credit-card?
  [number]
  (let [arr-number-card (map #(read-string (str %)) number)
        sum (reduce + (change-digits arr-number-card))]
    (zero? (mod sum 10))))

(deftest test-asserts
  (assert (not (valid-credit-card? "1234567890123456")))
  (assert (valid-credit-card? "4408041234567893"))
  (assert (not (valid-credit-card? "4408042234567893")))
  (assert (valid-credit-card? "38520000023237"))
  (assert (valid-credit-card? "4222222222222")))
