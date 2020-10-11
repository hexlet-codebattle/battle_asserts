(ns battle-asserts.issues.how-many-digits
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def description "Imagine you took all the numbers between 0 and n and concatenated them together into a long string. How many digits are there between 0 and n? Write a function that can calculate this.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 0 :arguments [1]}
   {:expected 9 :arguments [10]}
   {:expected 189 :arguments [100]}
   {:expected 6973 :arguments [2021]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 0 100000)))

(defn solution [num]
  (count (s/join (range 1 num))))
