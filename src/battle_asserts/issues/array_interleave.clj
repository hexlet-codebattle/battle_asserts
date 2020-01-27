(ns battle-asserts.issues.array-interleave
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Construct a new array by interleaving two input arrays.
                  Interleaving is achieved by taking values from each array consecutively:
                  1st value from the 1st array, then 1st value from the 2nd array, then 2nd value from the
                  1st array, then 2nd value from the 2nd array, etc. Arrays can be of different lengths,
                  so if one arrays ends before the other, stop adding new values and return.")

(def signature
  {:input [{:argument-name "arr1" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "arr2" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/list gen/small-integer) (gen/list gen/small-integer)))

(def test-data
  [{:expected [5 4 1 9]
    :arguments [[5 1 3] [4 9]]}
   {:expected [1 2 3 4 5 6]
    :arguments [[1 3 5] [2 4 6]]}
   {:expected [1 2 3 4]
    :arguments [[1 3 5] [2 4]]}
   {:expected []
    :arguments [[] [2 4 6]]}])

(defn solution [array-first array-second]
  (->>
   array-first
   (map-indexed #(vector %2 (nth array-second %1 nil)))
   flatten
   (filter #(not (nil? %)))
   (take (* 2 (min (count array-first) (count array-second))))))
