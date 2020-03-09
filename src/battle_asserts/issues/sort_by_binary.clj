(ns battle-asserts.issues.sort-by-binary
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Sort an array of integers by the number of 1's in its binary representation (in ascending order). If two integers have the same number of 1's in their binary representation,
                  their relative order should be the same as in the original array.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer 2 20)))

(def test-data
  [{:expected [1 2 4 3]
    :arguments [[1 2 3 4]]}
   {:expected [8 9 6 7]
    :arguments [[9 8 7 6]]}
   {:expected [64 5 7 255]
    :arguments [[255 7 5 64]]}
   {:expected [4 2 5 7]
    :arguments [[5 4 2 7]]}])

(defn binary-count [i]
  (->> i
       Integer/toBinaryString
       (filter #(= % \1))
       count))

(defn solution [arr]
  (sort-by binary-count arr))
