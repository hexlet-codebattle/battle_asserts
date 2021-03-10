(ns battle-asserts.issues.max-abs-elem
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Find the maximum absolute value of an array.")

(def signature
  {:input [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/vector (gen/choose -20 20) 2 5)))

(def test-data
  [{:expected 4
    :arguments [[1 -2 3 4]]}
   {:expected 16
    :arguments [[2 0 -16 -1]]}
   {:expected 1
    :arguments [[-1, -1]]}
   {:expected 122
    :arguments [[122, -113, 100]]}])

(defn solution [arr]
  (if (empty? arr)
    -1 (apply max (map #(Math/abs %) arr))))
