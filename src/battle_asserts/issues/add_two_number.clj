(ns battle-asserts.issues.add-two-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Calculate digits sum of two-digit integer `n`.")

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 10 99)))

(def test-data
  [{:expected 3 :arguments [12]}
   {:expected 5 :arguments [23]}
   {:expected 1 :arguments [10]}
   {:expected 18 :arguments [99]}])

(defn solution [n]
  (let [first-num (/ n 10)
        second-num (rem n 10)]
    (int (+ first-num second-num))))
