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
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (filter #(> (second %) 1))
   ffirst))
