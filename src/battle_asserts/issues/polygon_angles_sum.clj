(ns battle-asserts.issues.polygon-angles-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an n-sided regular polygon `n`. Calculate total sum of internal angles (in degrees).")

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 3 20)))

(def test-data
  [{:expected 180 :arguments [3]}
   {:expected 360 :arguments [4]}
   {:expected 720 :arguments [6]}])

(defn solution [n]
  (* (- n 2) 180))
