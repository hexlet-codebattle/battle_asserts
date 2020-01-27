(ns battle-asserts.issues.count-pair-with-difference-equal-to-k
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.")

(def signature
  {:input [{:argument-name "pairs" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "k" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer) gen/nat))

(def test-data
  [{:expected 2
    :arguments [[1 5 3 4 2] 3]}
   {:expected 5
    :arguments [[8 12 16 4 0 20] 4]}
   {:expected 7
    :arguments [[1 4 3 0 2 5 7 8 9 6] 3]}
   {:expected 3
    :arguments [[1 2 3 4 4 2 2 1] 0]}])

(defn make-pair [value array]
  (mapv vector (repeat (count array) value)
        array))

(defn solution [arr k]
  (->>
   arr
   (reduce-kv #(concat %1 (make-pair %3 (subvec arr (inc %2)))) [])
   (filter #(= k (Math/abs (apply - %))))
   distinct
   count))
