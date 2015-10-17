(ns battle-asserts.issues.disjoint-sets
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.set :as s]))

(def level :easy)

(def description "Given two sets represented by two arrays, how to check if the given two sets are disjoint or not?
                 It may be assumed that the given arrays have no duplicates.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int) (gen/vector gen/int)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [first-set second-set]
  (empty? (s/intersection (set first-set)
                          (set second-set))))
