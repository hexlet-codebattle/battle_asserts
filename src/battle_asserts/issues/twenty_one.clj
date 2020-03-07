(ns battle-asserts.issues.twenty-one
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an array, determine if the sum of all its elements is equal to 21.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(seq-with-sum [value]
            (loop [sum 0 result []]
              (if (= sum value)
                result
                (let [new-element (rand-int (inc (- value sum)))]
                  (recur (+ sum new-element) (conj result new-element))))))]
    (gen/tuple (gen/one-of [(gen/vector gen/small-integer)
                            (gen/elements (repeatedly 50 #(seq-with-sum 21)))]))))

(def test-data
  [{:expected true
    :arguments [[3 4 5 6 3]]}
   {:expected true
    :arguments [[11 10]]}
   {:expected false
    :arguments [[3 11 10]]}
   {:expected false
    :arguments [[10 10]]}])

(defn solution [n]
  (= (reduce + n) 21))
