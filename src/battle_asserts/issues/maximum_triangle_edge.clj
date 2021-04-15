(ns battle-asserts.issues.maximum-triangle-edge
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description "Create a function that finds the maximum range of a triangle's third edge, where the side lengths are all integers.")

(def signature
  {:input  [{:argument-name "first" :type {:name "integer"}}
            {:argument-name "second" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 17 :arguments [8 10]}
   {:expected 11 :arguments [5 7]}
   {:expected 10 :arguments [9 2]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 1 20) (gen/choose 1 20)))

(defn solution [side1 side2]
  (dec (+ side1 side2)))
