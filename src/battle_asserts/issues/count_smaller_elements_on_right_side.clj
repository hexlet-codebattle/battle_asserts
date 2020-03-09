(ns battle-asserts.issues.count-smaller-elements-on-right-side
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "For each element X of the given array, count how many elements to the right of X are smaller than X. The last value is always zero since there are no elements to the right of the last element.")

(def signature
  {:input [{:argument-name "arr"
            :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer)))

(def test-data
  [{:expected [6 1 1 1 0 1 0]
    :arguments [[12 1 2 3 0 11 4]]}
   {:expected [4 3 2 1 0]
    :arguments [[5 4 3 2 1]]}
   {:expected [0 0 0 0 0]
    :arguments [[1 2 3 4 5]]}
   {:expected [6 5 7 4 1 3 0 2 0 0]
    :arguments [[7 6 10 5 2 8 1 9 3 4]]}])

(defn count-smaller [value array]
  (count (filter #(> value %) array)))

(defn solution [array]
  (map-indexed #(count-smaller %2 (subvec array (inc %1))) array))
