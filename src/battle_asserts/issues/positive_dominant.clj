(ns battle-asserts.issues.positive-dominant
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Create a function, thats check if unique positive numbers are greater than negative ones in `numbers` array."
   :ru "Создайте функцию, которая проверяет, больше ли уникальных положительных чисел, чем отрицательных, в массиве `numbers`."})

(def signature
  {:input  [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -5 5) 10 18)))

(def test-data
  [{:expected true :arguments [[1 2 3 -3 -4]]}
   {:expected true :arguments [[1 1 2 2 3 3 4 -4]]}
   {:expected false :arguments [[1 -1 -2 -3 -4]]}
   {:expected false :arguments [[10 1 -2 -2 -3 -4]]}
   {:expected false :arguments [[1 2 3 -3 -4 -5]]}])

(defn solution [numbers]
  (let [uniq (distinct numbers)
        pos-numbers (filter pos? uniq)
        neg-numbers (filter neg? uniq)]
    (> (count pos-numbers)
       (count neg-numbers))))
