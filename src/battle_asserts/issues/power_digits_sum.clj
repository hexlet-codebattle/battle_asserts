(ns battle-asserts.issues.power-digits-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Calculate the sum of digits of 2\\*\\*n
                 For example, 2\\*\\*15 = 32768, and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.")

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 30)))

(def test-data
  [{:expected 13
    :arguments [8]}
   {:expected 26
    :arguments [15]}])

(defn solution [n]
  (reduce + (map #(Character/digit % 10)
                 (str (apply * (repeat n 2))))))
