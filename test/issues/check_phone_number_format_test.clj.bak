(ns battle-solutions.check-phone-number-format-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn check-phone-number-format
  [candidate]
  (not (nil? (re-matches #"^((8|0|((\+|00)\d{1,2}))[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{6,12}$"
                         candidate))))

(deftest test-asserts
  (let [input "5555555555"]
    (assert-true (check-phone-number-format input)))
  (let [input "555555555"]
    (assert-true (check-phone-number-format input)))
  (let [input "555-5555"]
    (assert-true (check-phone-number-format input)))
  (let [input "(555) 555-5555"]
    (assert-true (check-phone-number-format input)))
  (let [input "(555) 555-555"]
    (assert-true (check-phone-number-format input)))
  (let [input "(555) 555-555-5555"]
    (assert-true (check-phone-number-format input)))
  (let [input "(555) 555a-555-5555"]
    (assert-true (not (check-phone-number-format input))))
  (let [input "555*-555-5555"]
    (assert-true (not (check-phone-number-format input))))
  (let [input "55a-555-5555"]
    (assert-true (not (check-phone-number-format input))))
  (let [input "55-55-55"]
    (assert-true (check-phone-number-format input)))
  (let [input "55 55 55"]
    (assert-true (check-phone-number-format input))))
