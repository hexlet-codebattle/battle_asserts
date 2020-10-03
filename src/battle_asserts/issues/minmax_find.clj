(ns battle-asserts.issues.minmax-find
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that takes an array of numbers and return both the minimum and maximum numbers, in that order.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -50 50) 3 10)))

(def test-data
  [{:expected [-3 22] :arguments [[-3 2 10 22]]}
   {:expected [0 1] :arguments [[0 0 0 1 1 1]]}
   {:expected [30 32] :arguments [[30 30 31 32]]}])

(defn solution [numbers]
  (let [minimum (apply min numbers)
        maximum (apply max numbers)]
    [minimum maximum]))
