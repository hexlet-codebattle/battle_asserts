(ns battle-asserts.issues.domino
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "The input contains the maximum number of dots on one end of a domino bone.
                 Output the number of dots on the domino set.
                 Sample 2 -> 12")

(def signature
  {:input  [{:argument-name "x" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple gen/nat))

(def test-data
  [{:expected 12
    :arguments [2]}
   {:expected 0
    :arguments [0]}
   {:expected 3
    :arguments [1]}
   {:expected 30
    :arguments [3]}])

(defn solution [x]
  (letfn [(rat [x]
            (+ (reduce + (range (inc x)))
               (* x (inc x))))]
    (->> (range (inc x))
         (map rat)
         (reduce +))))
