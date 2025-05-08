(ns battle-asserts.issues.count-pair-with-difference-equal-to-k
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["collections"])

(def description
  {:en "Given an integer array and a positive integer k, count all distinct pairs with difference equal to k. Order of numbers matters."
   :ru "Дан массив целых чисел и положительное целое число k, сосчитайте все различные пары чисел, разность которых по модулю равна k. Порядок чисел важен."})

(def signature
  {:input [{:argument-name "pairs" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "k" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 0 20) 5 10) (gen/choose 0 10)))

(def test-data
  [{:expected  2
    :arguments [[1 5 3 4 2] 3]}
   {:expected  5
    :arguments [[8 12 16 4 0 20] 4]}
   {:expected  7
    :arguments [[1 4 3 0 2 5 7 8 9 6] 3]}
   {:expected  3
    :arguments [[1 2 3 4 4 2 2 1] 0]}])

(defn solution [arr k]
  (let [get-pairs (fn [x coll]
                    (for [y coll]
                      [x y]))
        pairs     (mapcat get-pairs arr (iterate rest (rest arr)))]
    (->> pairs
         distinct
         (filter #(= k (Math/abs (apply - %))))
         count)))
