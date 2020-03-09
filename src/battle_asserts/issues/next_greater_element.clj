(ns battle-asserts.issues.next-greater-element
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array, return the Next Greater Element (NGE) for every element.
                 The Next greater Element for an element x is the first greater element on the right side of x in array.
                 Elements for which no greater element exist, consider next greater element as -1.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer)))

(def test-data
  [{:expected [5 25 25 -1]
    :arguments [[4 5 2 25]]}
   {:expected [-1 12 12 -1]
    :arguments [[13 7 6 12]]}
   {:expected [6 8 12 5 5 12 -1 9 -1]
    :arguments [[3 6 8 2 1 5 12 4 9]]}
   {:expected [4 2 4 -1]
    :arguments [[3 1 2 4]]}])

(defn max-than [value in]
  (first (filter #(< value %) in)))

(defn solution [array]
  (map-indexed #(or
                 (max-than %2 (subvec array (inc %1)))
                 -1)
               array))
