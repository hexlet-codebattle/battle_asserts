(ns battle-asserts.issues.partiphify
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that will divide the list by a specified number of parts")

(def signature
  {:input  [{:argument-name "integers" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "parts" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(def test-data
  [{:expected [[1], []] :arguments [[1], 2]}
   {:expected [[1] [2] [3]] :arguments [[1 2 3], 3]}
   {:expected [[1 3 5] [2 4]] :arguments [[1 2 3 4 5], 2]}])
