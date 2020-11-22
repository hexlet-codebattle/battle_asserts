(ns battle-asserts.issues.largest-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an integer `n`, return the largest number that contains exactly `n` digits.")

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 10)))

(def test-data
  [{:expected 9 :arguments [1]}
   {:expected 99 :arguments [2]}
   {:expected 9999999 :arguments [7]}])

(defn solution [n]
  (int (dec (Math/pow 10 n))))
