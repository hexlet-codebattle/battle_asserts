(ns battle-asserts.issues.largest-pair-sum-in-array
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array of integers, find the largest pair sum in it. For example,
                 the largest pair sum in [12, 34, 10, 6, 40] is 74.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer)))

(def test-data
  [{:expected 1
    :arguments [[1]]}
   {:expected 11
    :arguments [[1 2 3 4 5 6]]}
   {:expected 74
    :arguments [[12 34 10 6 40]]}
   {:expected 80
    :arguments [[12 40 10 6 40]]}
   {:expected 52
    :arguments [[12 -34 10 6 40]]}])

(defn solution [array]
  (apply + (take-last 2 (sort array))))
