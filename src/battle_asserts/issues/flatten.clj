(ns battle-asserts.issues.flatten
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Given an array, possibly with more arrays inside, return a 1-dimensional
                  flat array with all the values in the initial order.")

(def disabled true)

(defn arguments-generator
  []
  (let [nested (gen/list (gen/one-of [gen/small-integer (gen/list gen/small-integer)]))]
    (gen/tuple (gen/resize 5 (gen/list (gen/one-of [gen/small-integer nested]))))))

; (gen/sample (arguments-generator) 1)

(def test-data
  [{:expected [1 2 {:a "b"}]
    :arguments [[1 [2 [{:a "b"}]]]]}
   {:expected [1 2 3 4 5 6 7 8]
    :arguments [[1 [2 3 [4 5 [6 7]]] 8]]}
   {:expected []
    :arguments [[]]}
   {:expected [1 2 3 4]
    :arguments [[[1 2] [3 4]]]}])

(defn solution
  [x]
  (remove sequential?
          (rest (tree-seq sequential? seq x))))
