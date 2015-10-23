(ns battle-asserts.issues.factorial-trailing-zeroes
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function which returns the number of trailing zeroes in n!")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 90)))

(def test-data
  [{:expected 0
    :arguments [0]}
   {:expected 1
    :arguments [5]}
   {:expected 1
    :arguments [7]}
   {:expected 4
    :arguments [23]}
   {:expected 6
    :arguments [28]}
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
