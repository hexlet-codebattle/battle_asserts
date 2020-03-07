(ns battle-asserts.issues.array-transpose
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Implement the matrix transposition function.
                  Matrices are presented as arrays of arrays, where internal arrays are rows of the matrix.
                  There are different ways to transpose a matrix:
                  1) reflect the array over its main diagonal (which runs from top-left to bottom-right);
                  2) write the rows of the original matrix as columns of the new one;
                 3) write the columns of the original matrix as rows of the new one.")

(def signature
  {:input [{:argument-name "arr1" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "arr2" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}})

(defn arguments-generator []
  (gen/tuple (gen/bind (gen/choose 2 5)
                       #(gen/vector (gen/vector gen/small-integer %)))))

(def test-data
  [{:expected [[1 10] [2 20] [3 30]]
    :arguments [[[1 2 3] [10 20 30]]]}
   {:expected [[1 3 5] [2 4 6]]
    :arguments [[[1 2] [3 4] [5 6]]]}
   {:expected []
    :arguments [[]]}])

(defn solution [vectors]
  (if (not-empty vectors)
    (apply mapv vector vectors)
    []))
