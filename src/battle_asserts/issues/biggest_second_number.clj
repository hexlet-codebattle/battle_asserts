(ns battle-asserts.issues.biggest-second-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Find the largest second number in array.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/small-integer)))

(def test-data
  [{:expected 2 :arguments [[1 2 3]]}
   {:expected 1 :arguments [[1 -2 3]]}
   {:expected 0 :arguments [[0 0 10]]}
   {:expected -2 :arguments [[-1 -2 -3]]}])

(defn solution [numbers]
  (second (reverse (sort numbers))))
