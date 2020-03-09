(ns battle-asserts.issues.array-fetch
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an array of integers, an index, and a default value as input, return
                 the element by that index; if no element exists with given index then return the default value.
                 Index can be a negative integer, which means going over the array backwards from the end.")

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "index" :type {:name "integer"}}
           {:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/small-integer)
             gen/small-integer
             gen/small-integer))

(def test-data
  [{:expected 2 :arguments [[1 2 3] 1 10]}
   {:expected 10 :arguments [[1 2 3] 5 10]}
   {:expected 3 :arguments [[1 2 3] -1 10]}
   {:expected 10 :arguments [[1 2 3] -5 10]}
   {:expected 1 :arguments [[1 -5 4 2] 0 0]}
   {:expected 0 :arguments [[8 0 6 7] -3 -8]}])

(defn solution
  [s index default]
  (let [positive-index (if (neg? index) (+ (count s) index) index)]
    (nth s positive-index default)))
