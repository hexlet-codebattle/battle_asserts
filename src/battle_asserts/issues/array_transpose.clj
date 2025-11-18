;; Moved to modern repository
(ns battle-asserts.issues.array-transpose
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections" "math"])

(def description
  {:en "Implement the matrix transposition function.
        Matrices are presented as arrays of arrays, where internal arrays are rows of the matrix.
        There are different ways to transpose a matrix:
        1) reflect the array over its main diagonal (which runs from top-left to bottom-right);
        2) write the rows of the original matrix as columns of the new one;
        3) write the columns of the original matrix as rows of the new one."
   :ru "Реализуйте функцию транспонирования матрицы.
        Матрицы представлены массивом массивов, где внутренние массивы - это строки матрицы.
        Существуют различные способы транспонирования матрицы:
        1) Отразить матрицу по главной диагонали (которая проходит от верхнего левого угла до нижнего правого);
        2) Записать строки исходной матрицы как столбцы новой матрицы;
        3) Записать столбцы исходной матрицы как строки новой матрицы."})

(def signature
  {:input [{:argument-name "matrix" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}})

(defn arguments-generator []
  (let [cols (rand-nth (range 2 10))
        rows (rand-nth (range 1 10))]
    (gen/tuple (gen/vector (gen/vector gen/small-integer rows) cols))))

(def test-data
  [{:expected [[1 10] [2 20] [3 30]]
    :arguments [[[1 2 3] [10 20 30]]]}
   {:expected [[1 3 5] [2 4 6]]
    :arguments [[[1 2] [3 4] [5 6]]]}
   {:expected [[1 2 3]]
    :arguments [[[1]
                 [2]
                 [3]]]}])

(defn solution [matrix]
  (apply mapv vector matrix))
