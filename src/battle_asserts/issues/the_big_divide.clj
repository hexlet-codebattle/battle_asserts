(ns battle-asserts.issues.the-big-divide
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function which calculates the sum of all natural numbers under n (first argument)
                 which are evenly divisible by at least one of a and b (second and third argument)")

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
