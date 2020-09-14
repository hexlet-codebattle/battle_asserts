(ns battle-asserts.issues.smallest-second-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Find the smallest second number in array.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/vector (gen/choose -10 10) 3 10)))

(def test-data
  [{:expected 2 :arguments [[1 2 3]]}
   {:expected 1 :arguments [[1 -2 3]]}
   {:expected 0 :arguments [[0 0 10]]}
   {:expected 1 :arguments [[1 -2 5 3]]}
   {:expected -2 :arguments [[1 -2 -5 3]]}
   {:expected -3 :arguments [[-1 -5 -3]]}])

(defn solution [numbers]
  (second (sort numbers)))