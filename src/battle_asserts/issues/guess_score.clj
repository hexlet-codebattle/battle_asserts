(ns battle-asserts.issues.guess-score
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description
  "Write a function that takes the assumed and real soccer match scores as the arguments and returns
   2 if the score is correct,
   1 if the winner is correct but the score is wrong and
   0 if the winner is wrong.")

(def signature
  {:input [{:argument-name "guess" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "actual" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected 0
    :arguments [[1 2] [2 1]]}
   {:expected 1
    :arguments [[2 2] [3 3]]}
   {:expected 2
    :arguments [[3 1] [3 1]]}
   {:expected 2
    :arguments [[1 1] [1 1]]}])

(defn solution [[g1 g2] [a1 a2]]
  (cond
    (= [g1 g2] [a1 a2])              2       ;; bet correct
    (= (- g1 g2) (- a1 a2) 0)        1       ;; draw correct 
    (pos? (* (- g1 g2) (- a1 a2)))   1       ;; winner correct
    :else                            0))
