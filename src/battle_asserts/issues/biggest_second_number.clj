;; Moved to modern repository
(ns battle-asserts.issues.biggest-second-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "collections"])

(def description
  {:en "Find the largest second number in array."
   :ru "Найдите второе наибольшее число в массиве."})

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/vector gen/small-integer 3)))

(def test-data
  [{:expected 2 :arguments [[1 2 3]]}
   {:expected 1 :arguments [[1 -2 3]]}
   {:expected 0 :arguments [[0 0 10]]}
   {:expected -2 :arguments [[-1 -2 -3]]}])

(defn solution [numbers]
  (second (reverse (sort numbers))))
