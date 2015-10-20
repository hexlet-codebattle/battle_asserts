(ns battle-asserts.issues.disjoint-sets
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.set :as s]))

(def level :easy)

(def description "Given two sets represented by two arrays, check if the given two sets are disjoint or not. Given arrays have no duplicates.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int) (gen/vector gen/int)))

(def test-data
  [{:expected true
    :arguments [[12 34 11 9 3] [7 2 1 5]]}
   {:expected false
    :arguments [[12 34 11 9 3] [2 1 3 5]]}
   {:expected true
    :arguments [[15 16 7 2 1] [14 20 8 6 0]]}
   {:expected false
    :arguments [[1 2 4 5 8 9] [2 1 3 5 9]]}])

(defn solution [first-set second-set]
  (empty? (s/intersection (set first-set)
                          (set second-set))))
