(ns battle-asserts.issues.credit-card-validator
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Credit card numbers can be validated with a process called the Luhn algorithm.
                 Simply stated, the Luhn algorithm works like this:
                 1. From the rightmost digit, which is the check digit, moving left, double the value of every second
                 digit; if the product of this doubling operation is greater than 9 (e.g., 8 × 2 = 16), then sum the
                 digits of the products (e.g., 16: 1 + 6 = 7, 18: 1 + 8 = 9).
                 2. Take the sum of all the digits.
                 3. If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid
                 according to the Luhn formula; else it is not valid.")

(def signature
  {:input [{:argument-name "arr" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(valid-credit-card-number []
            (let [length (+ 12 (rand-int 5))
                  digits (vec (repeatedly (dec length) #(rand-int 10)))
                  get-digit #(->> (* 2 %) (str) (map (comp read-string str)) (reduce +))
                  sum (reduce-kv #(+ %1 (if (even? %2) (get-digit %3) %3)) 0 digits)
                  modulo (mod sum 10)
                  check-digit (if (zero? modulo) 0 (- 10 modulo))]
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

(defn solution
  [number]
  (let [to-digits #(map (comp read-string str) (str %))
        modify (fn [idx x]
                 (if (odd? idx)
                   (->> (* 2 x)
                        (to-digits)
                        (reduce +))
                   x))
        mod-10? #(zero? (mod % 10))]
    (->> number
         (to-digits)
         (reverse)
         (map-indexed modify)
         (reduce +)
         (mod-10?))))
