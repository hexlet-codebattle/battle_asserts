(ns battle-asserts.issues.disjoint-sets
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.set :as s]))

(def level :easy)

(def description "Given two sets represented by two arrays, how to check if the given two sets are disjoint or not?
                 It may be assumed that the given arrays have no duplicates.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int) (gen/vector gen/int)))

(defn solution [first-set second-set]
  (empty? (s/intersection (set first-set)
                          (set second-set))))
