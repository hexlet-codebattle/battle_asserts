(ns battle-asserts.issues.cube-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "Calculate sum of cubes in array from 1 to `n`."
   :ru "Рассчитайте сумму кубов в массиве от 1 до `n`."})

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1 30)))

(def test-data
  [{:expected 1 :arguments [1]}
   {:expected 9 :arguments [2]}
   {:expected 44100 :arguments [20]}
   {:expected 216225 :arguments [30]}])

(defn solution [n]
  (letfn [(cube [num] (* num num num))]
    (reduce (fn [acc elem] (+ acc (cube elem))) 0 (range 1 (inc n)))))
