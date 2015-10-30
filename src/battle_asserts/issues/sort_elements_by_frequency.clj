(ns battle-asserts.issues.sort-elements-by-frequency
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of integers, sort the array according to frequency of elements. 
                  Most frequent numbers come first.
                  If several groups of the same size exist, 
                  they should appear in the order of corresponding numbers in the input array.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int 4 20)))

(def test-data
  [{:expected [3 3 3 3 2 2 2 12 12 4 5]
    :arguments [[2 3 2 4 5 12 2 3 3 3 12]]}
   {:expected [2 2 2 3 4]
    :arguments [[2 2 2 3 4]]}
   {:expected [1 1 1 1 2 2 3 3 4]
    :arguments [[1 2 1 2 1 4 3 3 1]]}
   {:expected [8 8 7 7 2 2 1 1 9 9 6]
    :arguments [[8 6 8 7 2 2 7 1 9 9 1]]}])

(defn solution [array]
  (->>
   (frequencies array)
   (sort-by val #(compare %2 %1))
   (reduce #(concat %1 (repeat (second %2) (first %2))) [])))
