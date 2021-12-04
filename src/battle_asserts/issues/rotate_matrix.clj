(ns battle-asserts.issues.rotate-matrix
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["collections" "math"])

(def description
  {:en "Implement a function that rotates matrix."
   :ru "Создайте функцию которая переворачивает матрицу."})

(def signature
  {:input [{:argument-name "matrix" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}})

(defn arguments-generator []
  (let [cols (rand-nth (range 2 10))
        rows (rand-nth (range 1 10))]
    (gen/tuple (gen/vector (gen/vector gen/small-integer rows) cols))))

(def test-data
  [{:expected [[1 4]
               [2 5]
               [3 6]]
    :arguments [[[1 2 3]
                 [4 5 6]]]}
   {:expected [[1 5 9]
               [2 6 10]
               [3 7 11]
               [4 8 12]]
    :arguments [[[1 2 3 4]
                 [5 6 7 8]
                 [9 10 11 12]]]}
   {:expected [[1 2 3]]
    :arguments [[[1]
                 [2]
                 [3]]]}])

(defn solution [matrix]
  (apply mapv vector matrix))
