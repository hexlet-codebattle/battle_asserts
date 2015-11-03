(ns battle-asserts.issues.credit-card-validator
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "Credit card numbers can be validated with a process called the Luhn algorithm.
                 Simply stated, the Luhn algorithm works like this:
                 1. Starting with the next to last digit and continuing with every other digit going back to the beginning of the card, double the digit.
                 2. Sum all doubled and untouched digits in the number.
                 3. If that total is a multiple of 10, the number is valid.")

(defn- get-digit
  [digit]
  (let [digit-double (* digit 2)]
    (if (> digit-double  9)
      (- digit-double 9)
      digit-double)))

(defn arguments-generator []
  (letfn [(valid-credit-card-number []
            (let [length (+ 12 (rand-int 5))
                  digits (vec (repeatedly (dec length) #(rand-int 10)))
                  sum (reduce-kv #(+ %1 (if (even? %2) (get-digit %3) %3)) 0 digits)
                  modulo (mod sum 10)
                  check-digit (if  (zero? modulo) 0 (- 10 modulo))]
              (s/join (conj digits check-digit))))
          (random-credit-card-number []
                                     (s/join (repeatedly (+ 12 (rand-int 5)) #(rand-int 10))))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 random-credit-card-number))
                            (gen/elements (repeatedly 50 valid-credit-card-number))]))))

(def test-data
  [{:expected true
    :arguments ["4408041234567893"]}
   {:expected false
    :arguments ["1234567890123456"]}
   {:expected false
    :arguments ["4408042234567893"]}
   {:expected true
    :arguments ["38520000023237"]}
   {:expected true
    :arguments ["4222222222222"]}])

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

(defn solution [number]
  (let [arr-number-card (map #(read-string (str %)) number)
        sum (reduce + (change-digits arr-number-card))]
    (println number)
    (println sum)
    (zero? (mod sum 10))))
