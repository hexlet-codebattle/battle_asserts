(ns battle-asserts.issues.nested-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Calculate sum of two-dimensial array.")

(def signature
  {:input [{:argument-name "arr" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/vector gen/small-integer 1 5) 2 6)))

(def test-data
  [{:expected -33
    :arguments [[[-1 -28 14]
                 [-16 1 30 -12 8]
                 [-17 10]
                 [-26 2 -3 -9 14]]]}
   {:expected 86
    :arguments [[[16]
                 [16 20 24 8]
                 [1]
                 [-28]
                 [13 16]]]}
   {:expected -22
    :arguments [[[-1 11 -3 -30 -1]
                 [2]]]}])

(defn solution [arr]
  (reduce + (flatten arr)))
