(ns battle-asserts.issues.stairway-to-heaven
  (:require [clojure.string :as string]
            [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "N dicks randomly spread out of 100 stairs, there can be as many dicks as you want on one step. We gotta go down these stairs.
Every time you step on a stair-step with dicks, the brat ratio increases by the number of dicks.
You can go down one or two steps at a time. Write a function to descend the stairs minimizing the brat ratio.
The function receives an array with the number of dicks on each step and returns the minimum brat index.

Powered by Eugene Zaytsev.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 6
    :arguments [[1 0 3 5 10 0 11 1]]}
   {:expected 0
    :arguments [[1 0 1 0 1]]}
   {:expected 0
    :arguments [[0 1 0 1]]}])
