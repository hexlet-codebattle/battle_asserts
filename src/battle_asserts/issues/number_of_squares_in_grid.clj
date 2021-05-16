(ns battle-asserts.issues.number-of-squares-in-grid
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "Create a function that calculates the number of different squares in an `n * n` square grid."
   :ru "Создайте функцию, которая подсчитывает число различных квадртаров на квадратной сетке размера `n * n`."})

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 2 15)))

(def test-data
  [{:expected 5 :arguments [2]}
   {:expected 30 :arguments [4]}
   {:expected 55 :arguments [5]}])

(defn solution [number]
  (if (> number 1)
    (+ (int (Math/pow number 2))
       (solution (dec number)))
    1))
