(ns battle-solutions.get-alphabet-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn get-alphabet [string]
  (-> string
      seq
      distinct
      sort
      clojure.string/join))

(deftest test-asserts
  (let [input "asfsfdss"
        output "adfs"]
    (assert-equal output (get-alphabet input)))
  (let [input "acgtgcgagtg"
        output "acgt"]
    (assert-equal output (get-alphabet input)))
  (let [input "4123214"
        output "1234"]
    (assert-equal output (get-alphabet input)))
  (let [input "+++[><<]<-."
        output "+-.<>[]"]
    (assert-equal output (get-alphabet input))))
