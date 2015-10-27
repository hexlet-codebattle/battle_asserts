(ns battle-asserts.issues.array-fetch
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given an array, an index, and a default value as input, return
                 the element by that index; if no element exists with given index then return the default value.
                 Index can be a negative integer, which means going over the array backwards from the end.")

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/int)
             gen/int
             gen/int))

(def test-data
  [{:expected "b" :arguments [["a" "b" "c"] 1 "d"]}
   {:expected "d" :arguments [["a" "b" "c"] 5 "d"]}
   {:expected "c" :arguments [["a" "b" "c"] -1 "d"]}
   {:expected "d" :arguments [["a" "b" "c"] -5 "d"]}
   {:expected 1 :arguments [[1 -5 4 2] 0 0]}
   {:expected 0 :arguments [[8 0 6 7] -3 -8]}])

; (gen/sample (arguments-generator) 5)

(defn solution
  [s index default]
  (let [positive-index (if (neg? index) (+ (count s) index) index )]
    (nth s positive-index default)))
