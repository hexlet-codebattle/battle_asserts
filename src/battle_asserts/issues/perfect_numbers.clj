(ns battle-asserts.issues.perfect-numbers
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Determine if a given number is \"perfect\".
                  Perfect number is a positive integer that is equal to the sum of 
                  its proper positive divisors (the sum of its positive divisors excluding the number itself).")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

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
