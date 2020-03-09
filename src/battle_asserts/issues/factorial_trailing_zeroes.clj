(ns battle-asserts.issues.factorial-trailing-zeroes
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Return the number of trailing zeroes in n! 
  For example, 5! = 120, the number of trailing zeros is 1; 10! = 3 628 800, the number of trailing zeros is 2.")

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 90)))

(def test-data
  [{:expected 6
    :arguments [28]}
   {:expected 0
    :arguments [0]}
   {:expected 1
    :arguments [5]}
   {:expected 1
    :arguments [7]}
   {:expected 4
    :arguments [23]}
   {:expected 22
    :arguments [99]}])

(defn factorial [n]
  (apply *' (range 1 (inc n))))

(defn solution [n]
  (->>
   n
   factorial
   str
   (re-find #"0+$")
   count))
