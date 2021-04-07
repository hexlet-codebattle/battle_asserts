(ns battle-asserts.issues.boom
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "collections"])

(def description "Implement a function that takes an array of numbers and return \"Boom!\" if the number 7 appears. Otherwise, return \"There is no 7.\"")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -10 10) 2 10)))

(def test-data
  [{:expected "Boom!" :arguments [[1 2 3 7]]}
   {:expected "Boom!" :arguments [[7 7 10 2 3]]}
   {:expected "There is no 7." :arguments [[1 2 3 10 4 2]]}])

(defn solution [numbers]
  (if (contains? (set numbers) 7)
    "Boom!"
    "There is no 7."))
