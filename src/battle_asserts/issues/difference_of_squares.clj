(ns battle-asserts.issues.difference-of-squares
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Find the difference between the square of the sum and the sum of the squares of the first N natural numbers.
                 The square of the sum of the first ten natural numbers is,
                 (1 + 2 + ... + 10)^2 = 55^2 = 3025
                 The sum of the squares of the first ten natural numbers is,
                 1^2 + 2^2 + ... + 10^2 = 385
                 Hence, the difference between the sum of the squares of the first ten
                 natural numbers and the square of the sum is 3025 - 385 = 2640.")

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 120)))

(def test-data
  [{:expected 170 :arguments [5]}
   {:expected 25164150 :arguments [100]}
   {:expected 100100 :arguments [25]}
   {:expected 0 :arguments [1]}])

(defn square-of-sums [value]
  (let [sum (reduce + (range (inc value)))]
    (* sum sum)))

(defn sum-of-squares [value]
  (let [squares (map #(* % %) (range 1 (inc value)))]
    (reduce + squares)))

(defn solution [value]
  (- (square-of-sums value) (sum-of-squares value)))
