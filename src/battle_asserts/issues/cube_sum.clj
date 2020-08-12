(ns battle-asserts.issues.cube-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Calculate array sum from 1 to n.")

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1 30)))

(def test-data
  [{:expected 0 :arguments [0]}
   {:expected 1 :arguments [1]}
   {:expected 9 :arguments [2]}
   {:expected 36 :arguments [3]}
   {:expected 100 :arguments [4]}
   {:expected 3025 :arguments [10]}
   {:expected 14400 :arguments [15]}
   {:expected 44100 :arguments [20]}
   {:expected 216225 :arguments [30]}
   {:expected 0 :arguments [-30]}])

(defn solution [n]
  (letfn [(cube [num] (* num num num))]
    (reduce (fn [acc elem] (+ acc (cube elem))) 0 (range 1 (inc n)))))
