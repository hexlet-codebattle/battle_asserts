(ns battle-asserts.issues.perfect-numbers
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Determine if a given number is \"perfect\".
                 #FIXME Совершенное число - натуральное число, равное сумме всех своих собственных делителей (т.е всех положительных
                 делителей отличных от самого числа)")

(defn arguments-generator []
  (let [perfect-numbers [6 28 496 8128]]
    (gen/tuple (gen/one-of [(gen/choose 0 1000)
                            (gen/elements perfect-numbers)]))))

(def test-data
  [{:expected true
    :arguments [6]}
   {:expected false
    :arguments [7]}
   {:expected true
    :arguments [496]}
   {:expected false
    :arguments [500]}
   {:expected true
    :arguments [8128]}])

(defn solution [num]
  (letfn [(divisors [num]
            (filter #(zero? (rem num %))
                    (range 1 (inc (/ num 2)))))]
    (if (<= num 1)
      false
      (= num (reduce + (divisors num))))))
