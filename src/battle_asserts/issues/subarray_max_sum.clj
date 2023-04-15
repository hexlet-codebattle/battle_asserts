(ns battle-asserts.issues.subarray-max-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["array" "algorithm"])

(def description
  {:en "Given a two-dimensional integer array, find the largest sum of a subarray that is formed by picking one element from each row. The subarray can be of any length, but the element picked from each row must be different from those picked in other rows."
   :ru "Дан двумерный массив целых чисел, найдите наибольшую сумму подмассива, который формируется выбором одного элемента из каждой строки. Подмассив может иметь любую длину, но элемент, выбранный из каждой строки, должен отличаться от тех, которые выбраны в других строках."})

(def signature
  {:input [{:argument-name "matrix" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/vector gen/vector gen/int))

(def test-data
  [{:expected 10
    :arguments [[[1 2 3] [4 5 6] [7 8 9]]]}
   {:expected 12
    :arguments [[[1 2 3] [4 5 6] [7 8 1]]]}
   {:expected 18
    :arguments [[[1 2 3] [4 5 6] [7 1 1]]]}])

(defn solution [matrix]
  (let [rows (count matrix)
        cols (count (first matrix))
        cache (vec (repeat (Math/pow 2 rows) (vec (repeat cols 0))))]
    (loop [row 0, mask 0, sum 0, max-sum -1]
      (if (= row rows)
        max-sum
        (loop [col 0, current-sum 0, current-max-sum -1]
          (if (= col cols)
            (recur (inc row) mask (+ sum current-max-sum) (max max-sum (+ sum current-max-sum)))
            (let [value (nth (nth matrix row) col)
                  new-mask (bit-or mask (bit-shift-left 1 row))
                  memo (nth cache new-mask)]
              (recur (inc col)
                     (+ current-sum value)
                     (max current-sum (+ (nth memo col) value))
                     (max current-max-sum (+ (nth memo col) value))))))))))

