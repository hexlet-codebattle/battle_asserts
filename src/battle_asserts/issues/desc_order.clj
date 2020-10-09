(ns battle-asserts.issues.desc-order
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Your task is to make a function that can take any non-negative integer as an argument and return it with its digits in descending order.
  Essentially, rearrange the digits to create the highest possible number.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 54421
    :arguments [42145]}
   {:expected 654321
    :arguments [145263]}
   {:expected 123456789
    :arguments [987654321]}])