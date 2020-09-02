(ns battle-asserts.issues.additive-inverse
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Implement a function that returns an array of additive inverses.
                  A number added with its `additive inverse` equals zero.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/small-integer)))

(def test-data
  [{:expected [-1 2 -3 4 5] :arguments [[1 -2 3 -4 -5]]}
   {:expected [-1 -1 -1 0] :arguments [[1 1 1 0]]}
   {:expected [5 -25 -125] :arguments [[-5 25 125]]}])

(defn solution [numbers]
  (reduce (fn [acc num] (conj acc (* num -1))) [] numbers))
