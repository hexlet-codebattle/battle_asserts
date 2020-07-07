(ns battle-asserts.issues.biggest-square-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Implement function that take three numbers and returns square sum of biggest two.")

(def signature
  {:input [{:argument-name "a" :type {:name "integer"}}
           {:argument-name "b" :type {:name "integer"}}
           {:argument-name "c" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer
             gen/small-integer
             gen/small-integer))

(def test-data
  [{:expected 13 :arguments [1 2 3]}
   {:expected 2 :arguments [1 1 1]}
   {:expected 8 :arguments [1 2 2]}
   {:expected 45 :arguments [6 3 2]}
   {:expected 41 :arguments [2 4 5]}])

(defn solution [a b c]
  (letfn [(square [x] (* x x))
        (sum-of-squares [x y] (+ (square x) (square y)))]
    (cond
      (and (>= a c) (>= b c)) (sum-of-squares a b)
      (and (>= b a) (>= c a)) (sum-of-squares b c)
      (and (>= a b) (>= c b)) (sum-of-squares a c))))