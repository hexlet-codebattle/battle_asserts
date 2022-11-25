(ns battle-asserts.issues.domino
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["games"])

(def description
  {:en "The input contains the maximum number of dots on one end of a domino bone. Domino set is all possible combinations of domino bones. Output the number of dots on the domino set. Sample 2 -> 12, 1 -> 3 (0|1, 1|1)"
   :ru "`x` содержит максимальное количество точек на одном конце доминошки. Набор домино, это набор всех возможных комбинаций костяшек домино. Выведите количество точек в наборе домино. Например 2 -> 12, 1 -> 3 (0|1, 1|1)"})

(def signature
  {:input  [{:argument-name "x" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 4)))

(def test-data
  [{:expected 12
    :arguments [2]}
   {:expected 0
    :arguments [0]}
   {:expected 3
    :arguments [1]}
   {:expected 30
    :arguments [3]}])

(defn solution [x]
  (letfn [(rat [x]
            (+ (reduce + (range (inc x)))
               (* x (inc x))))]
    (->> (range (inc x))
         (map rat)
         (reduce +))))
