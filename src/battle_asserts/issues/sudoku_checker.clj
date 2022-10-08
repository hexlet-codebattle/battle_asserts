(ns battle-asserts.issues.sudoku-checker
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def disabled true)

(def tags ["collections" "math"])

(def description
  {:en "Check the validity of the solved Sudoku of size 4 x 4 with values from 1 to 4.
        According to sudoku rules, in each row, each column, and each small square, each digit is used only once."
   :ru "Проверьте валидность решенного судоку размером 4 х 4 и значениями от 1 до 4.
        По правилам судоку, в каждой строке, каждом столбце и каждом малом квадрате каждая цифра встречается только один раз."})

(def signature
  {:input [{:argument-name "matrix" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements
                          (repeatedly 30 (partial shuffle [1 2 3 4]))) 4)))

(def test-data
  [{:expected false
    :arguments [[[1 2 3 4] [1 2 3 4] [1 2 3 4] [1 2 3 4]]]}
   {:expected false
    :arguments [[[1 2 3 4] [4 2 2 1] [1 3 3 4] [1 2 3 4]]]}
   {:expected true
    :arguments [[[3 4 1 2] [1 2 4 3] [2 1 3 4] [4 3 2 1]]]}
   {:expected true
    :arguments [[[1 4 2 3] [3 2 1 4] [2 3 4 1] [4 1 3 2]]]}])

(defn cols [matrix]
  (mapv vector matrix))

(defn squares [matrix]
  (for [y (range 2)
        x (range 2)]
    (for [y' (range 2)
          x' (range 2)]
      (get-in matrix [(+ y' (* 2 y))
                      (+ x' (* 2 x))]))))

(defn solution [matrix]
  (letfn [(uniq? [coll] (= (count coll) (count (set coll))))
          (check-seq [matrix] (every? true? (map uniq? matrix)))]
    (and
     (check-seq matrix)
     (check-seq (cols matrix))
     (check-seq (squares matrix)))))
