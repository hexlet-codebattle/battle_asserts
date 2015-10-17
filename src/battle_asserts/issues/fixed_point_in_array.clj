(ns battle-asserts.issues.fixed-point-in-array
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of n distinct integers sorted in ascending order,
                 write a function that returns a Fixed Point in the array,
                 if there is any Fixed Point present in array, else returns -1.
                 Fixed Point in an array is an index i such that arr[i] is equal to i.")

(defn arguments-generator []
  (letfn [(inject-fixed-point [coll]
            (gen/bind (gen/tuple (gen/return coll) (gen/choose 0 (count coll)))
                      #(gen/return (assoc (first %) (second %) (second %)))))]
    (gen/tuple (gen/one-of [(gen/vector gen/int)
                            (gen/bind (gen/vector gen/int) inject-fixed-point)]))))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [array]
  (or
   (->>
    array
    (map-indexed vector)
    (filter #(= (first %) (second %)))
    ffirst)
   -1))
