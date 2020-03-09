(ns battle-asserts.issues.fixed-point-in-array
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array of n distinct integers sorted in ascending order,
                 return a Fixed Point in the array,
                 if there is a Fixed Point present in array; else return -1.
                 Fixed Point in an array is an index i such that arr[i] is equal to i.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(inject-fixed-point [coll]
            (gen/bind (gen/tuple (gen/return coll) (gen/choose 0 (count coll)))
                      #(gen/return (assoc (first %) (second %) (second %)))))]
    (gen/tuple (gen/one-of [(gen/vector gen/small-integer)
                            (gen/bind (gen/vector gen/small-integer) inject-fixed-point)]))))

(def test-data
  [{:expected 3
    :arguments [[-10, -5, 0, 3, 7]]}
   {:expected 0
    :arguments [[0, 2, 5, 8, 17]]}
   {:expected -1
    :arguments [[-10, -5, 3, 4, 7, 9]]}
   {:expected 3
    :arguments [[-3 -2 -1 3 4 7 8]]}])

(defn solution [array]
  (or
   (->>
    array
    (map-indexed vector)
    (filter #(= (first %) (second %)))
    ffirst)
   -1))
