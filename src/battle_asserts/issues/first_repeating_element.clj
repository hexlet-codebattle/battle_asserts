(ns battle-asserts.issues.first-repeating-element
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of integers, find the first repeating element in it.
                 We need to find the element that occurs more than once and whose index of first occurrence is smallest.")

(defn arguments-generator []
  (letfn [(gen-vector-with-repeating []
            (gen/bind (gen/tuple (gen/vector gen/int) gen/int)
                      #(gen/shuffle (concat (first %)
                                            (repeat 2 (second %))))))]
    (gen/tuple (gen/one-of [(gen-vector-with-repeating)
                            (gen/vector gen/int)]))))

(def test-data
  [{:expected 5
    :arguments [[10 5 3 4 3 5 6]]}
   {:expected 6
    :arguments [[6 10 5 4 9 120 4 6 10]]}
   {:expected 7
    :arguments [[6 10 7 4 9 120 4 7]]}
   {:expected 8
    :arguments [[8 8 7 4 9 120 4 7]]}])

(defn solution [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (filter #(> (second %) 1))
   ffirst))
