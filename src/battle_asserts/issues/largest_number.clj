(ns battle-asserts.issues.largest-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "Given an integer `n`, return the largest number that contains exactly `n` digits."
   :ru "Дано число `n`, найдите наибольшее число, которое содержит ровно `n` чисел."})

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 8)))

(def test-data
  [{:expected 9 :arguments [1]}
   {:expected 99 :arguments [2]}
   {:expected 9999999 :arguments [7]}])

(defn solution [n]
  (int (dec (Math/pow 10 n))))
