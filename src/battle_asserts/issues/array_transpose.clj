(ns battle-asserts.issues.array-transpose
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Implement the matrix transposition function.
                 Matrices are presented as arrays of arrays, where internal arrays are rows of the matrix.
                 There are several different ways to transpose a matrix:
                 1) reflect the array over its main diagonal (which runs from top-left to bottom-right);
                 2) write the rows of the original matrix as columns of the new one;
                 3) write the columns of the original matrix as rows of the new one.")

(defn arguments-generator []
  (gen/tuple (gen/bind (gen/choose 2 5)
                       #(gen/vector (gen/vector gen/int %)))))

(def test-data
  [{:expected [[1 :a] [2 :b] [3 :c]]
    :arguments [[[1 2 3] [:a :b :c]]]}
   {:expected [[1 3 5] [2 4 6]]
    :arguments [[[1 2] [3 4] [5 6]]]}
   {:expected []
    :arguments [[]]}])

(defn solution [vectors]
  (if (not-empty vectors)
    (apply mapv vector vectors)
    []))
