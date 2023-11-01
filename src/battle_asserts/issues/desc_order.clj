(ns battle-asserts.issues.desc-order
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "Your task is to make a function that can take any non-negative integer as an argument and return it with its digits in descending order.
  Essentially, rearrange the digits to create the highest possible number."
   :ru "Создайте функцию, которая из цифр заданного числа собирает наибольшее возможное число."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 0 :arguments [0]}
   {:expected 54421 :arguments [42145]}
   {:expected 654321 :arguments [145263]}
   {:expected 987654321 :arguments [987654321]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 0 1000000000)))

(defn solution [num]
  (Integer/parseInt (s/join "" (reverse (sort (str num))))))
