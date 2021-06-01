(ns battle-asserts.issues.the-big-divide
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "Calculate the sum of all natural numbers smaller than n (first argument)
        which are divisible by a or/and b (second and third arguments) without remainder."
   :ru "Вычислите сумму всех натуральных чисел меньше чем n (первый аргумент)
        которые делятся на a или b (второй и третий аргументы) без остатка."})

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}
            {:argument-name "a" :type {:name "integer"}}
            {:argument-name "b" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 2000) (gen/choose 1 100) (gen/choose 1 100)))

(def test-data
  [{:expected 23
    :arguments [10 3 5]}
   {:expected 0
    :arguments [3 17 11]}
   {:expected 233168
    :arguments [1000 3 5]}])

(defn solution [supr num1 num2]
  (reduce + (distinct (concat (range num1 supr num1)
                              (range num2 supr num2)))))
