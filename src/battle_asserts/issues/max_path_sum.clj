(ns battle-asserts.issues.max-path-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Write a function that takes a nested list of integers and returns the maximum sum of any path from the top of the list to the bottom."
   :ru "Напишите функцию, которая принимает вложенный список целых чисел и возвращает максимальную сумму любого пути от верхнего уровня списка до нижнего."})

(def signature
  {:input  [{:argument-name "lst" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/vector (gen/vector (gen/int -1000000000 1000000000)) 1 1000))

(def test-data
  [{:expected 23
    :arguments [[[3] [7 4] [2 4 6] [8 5 9 3]]]}
   {:expected 16
    :arguments [[[3] [7 4] [2 4 6] [1 5 9 3]]]}
   {:expected -2
    :arguments [[[3] [7 4] [-2 4 6] [8 5 9 3]]]}])
(defn solution [arr]
  (let [n (count arr)
        dp (vec (repeat n (vec (repeat n 0))))]

    (doseq [i (range n)]
      (aset dp i i (aget arr i)))

    (doseq [i (range (dec n) -1 -1)
            j (range (inc i) n)]
      (aset dp i j (max (+ (aget arr i) (min (if (< i (dec n)) (aget dp (inc i) j) (Integer/MAX_VALUE))
                                              (if (< j (dec n)) (aget dp (inc i) (inc j)) (Integer/MAX_VALUE))))
                        (+ (aget arr j) (min (if (< i (dec n)) (aget dp i (dec j)) (Integer/MAX_VALUE))
                                              (if (< j (dec n)) (aget dp (inc i) (dec j)) (Integer/MAX_VALUE)))))))

    (aget dp 0 (dec n))))
