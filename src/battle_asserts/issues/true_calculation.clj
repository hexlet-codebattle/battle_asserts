(ns battle-asserts.issues.true-calculation
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function which returns the number of true values there are in an array.")

(def signature
  {:input [{:argument-name "facts" :type {:name "array" :nested {:name "boolean"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/boolean 5 10)))

(def test-data
  [{:expected 4 :arguments [[true true false true false true]]}
   {:expected 5 :arguments [[false true false true true true true]]}
   {:expected 1 :arguments [[false false false true]]}])

(defn solution [facts]
  (count (filter true? facts)))
