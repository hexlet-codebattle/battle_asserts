(ns battle-asserts.issues.sum-of-array-plus-one
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "collections"])

(def description
  {:en "Given an array of integers, return the sum of integers after adding 1 to each one."
   :ru "Дается массив с числами, рассчитайте сумму чисел после добавления единицы к каждому."})

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -20 20)  4  10)))

(def test-data
  [{:expected 16
    :arguments [[12 -3 0 3]]}
   {:expected 14
    :arguments [[1 2 3 4]]}])

(defn solution [arr]
  (reduce #(+ %1 %2 1) 0 arr))
