(ns battle-asserts.issues.first-repeating-element
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array of integers, find the first repeating element in it.
                 Find the element that occurs more than once and whose index of first occurrence is  smallest.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(gen-vector-with-repeating []
            (gen/bind (gen/tuple (gen/vector gen/small-integer) gen/small-integer)
                      #(gen/shuffle (concat (first %)
                                            (repeat 2 (second %))))))]
    (gen/tuple (gen/one-of [(gen-vector-with-repeating)
                            (gen/vector gen/small-integer)]))))

(def test-data
  [{:expected 5
    :arguments [[10 5 3 4 3 5 6]]}
   {:expected 6
    :arguments [[6 10 5 4 9 120 4 6 10]]}
   {:expected 7
    :arguments [[6 10 7 4 9 120 4 7]]}
   {:expected 8
    :arguments [[8 8 7 4 9 120 4 7]]}
   {:expected -11
    :arguments [[-9 -11 -13 1 13 13 -2 -6 7 -11 -11 -2 7]]}
   {:expected -9
    :arguments [[3,-9,-4,11,15,-14,-1,11,1,-7,7,8,-9,-9,8,12]]}])

(defn solution [array]
  (->>
   array
   frequencies
   (filter #(> (val %) 1))
   keys
   (sort-by #(.indexOf array %))
   first))
