(ns battle-asserts.issues.how-many-digits
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Imagine you took all the numbers between 1 and `n` and concatenated them together into a long string. How many digits are there between 1 and `n`? Write a function that can calculate this."
   :ru "Представьте, что вы беретет все числа от 1 до `n` и соединяете их в одну большую строку. Как много цифр между 1 и `n`? Создайте функцию которая рассчитывает это."})

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 9 :arguments [10]}
   {:expected 189 :arguments [100]}
   {:expected 6973 :arguments [2021]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 2 100000)))

(defn solution [n]
  (count (s/join (range 1 n))))
