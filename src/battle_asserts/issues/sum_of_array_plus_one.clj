(ns battle-asserts.issues.sum-of-array-plus-one
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given an array of integers, return the sum of integers after adding 1 to each one.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int)))

(def test-data
  [{:expected 16
    :arguments [[12 -3 0 3]]}
   {:expected 14
    :arguments [[1 2 3 4]]}])

(defn solution [arr]
  (reduce #(+ %1 %2 1) 0 arr))
