(ns battle-asserts.issues.years-since-millennium
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Count how many years have passed since latest millennium.
                  For example, for 2015 the answer is 15.")

(def signature
  {:input  [{:argument-name "year" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/resize 10000 gen/nat)))

(def test-data
  [{:expected 15
    :arguments [2015]}
   {:expected 205
    :arguments [10205]}])

(defn solution
  [x]
  (mod x 1000))
