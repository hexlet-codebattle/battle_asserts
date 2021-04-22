(ns battle-asserts.issues.largest-swap
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description "Write a function that checks a two-digit number if it's the largest of two possible digit swaps.")

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 10 99)))

(def test-data
  [{:expected false :arguments [14]}
   {:expected false :arguments [27]}
   {:expected true :arguments [43]}
   {:expected true :arguments [99]}])

(defn solution [number]
  (>= (/ number 10) (mod number 10)))
