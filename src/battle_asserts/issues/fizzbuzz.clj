(ns battle-asserts.issues.fizzbuzz
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "If a number is divisible by 3, return \"Fizz\".
                 If a number is divisible by 5, return \"Buzz\".
                 If a number is divisible by 3 and 5, return \"FizzBuzz\".
                 Otherwise, return an empty string.")

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [divisible (take 20 (filter #(or (zero? (mod % 3))
                                        (zero? (mod % 5)))
                                   (range)))]
    (gen/tuple (gen/one-of [gen/small-integer
                            (gen/elements divisible)]))))

(def test-data
  [{:expected "Fizz"
    :arguments [3]}
   {:expected "Buzz"
    :arguments [50]}
   {:expected "FizzBuzz"
    :arguments [150]}
   {:expected "FizzBuzz"
    :arguments [5175]}])

(defn solution [number]
  (str
   (when (zero? (mod number 3)) "Fizz")
   (when (zero? (mod number 5)) "Buzz")))
