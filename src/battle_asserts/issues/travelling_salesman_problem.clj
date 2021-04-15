(ns battle-asserts.issues.travelling-salesman-problem
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description "Given a list of cities and the distances between each pair of cities,
                  what is the shortest possible route that visits each city and returns to the origin city?
                  Calculate the total number of possible paths that a salesman can travel with given `n` paths.")

(def signature
  {:input  [{:argument-name "paths" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 9)))

(def test-data
  [{:expected 1 :arguments [1]}
   {:expected 24 :arguments [4]}
   {:expected 40320 :arguments [8]}])

(defn solution [paths]
  (letfn [(factorial [n] (apply *' (range 1 (inc n))))]
    (factorial paths)))
