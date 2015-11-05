(ns battle-asserts.issues.credit-card-validator
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "Credit card numbers can be validated with a process called the Luhn algorithm.
                 Simply stated, the Luhn algorithm works like this:
                 1. If the length of the card number is even, pick all the digits with an even index. 
                 If the length of the card number is odd, pick all the digits with an odd index.
                 2. Transform each digit like so: if 2\\*x > 9, then replace x with 2\\*x - 9; 
                 othwerise, replace x with \\*2x
                 3. Add up all the numbers.
                 4. If the result is divisable by 10 (withour remainder) then the card number was valid.")

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
    (zero? (mod sum 10))))
