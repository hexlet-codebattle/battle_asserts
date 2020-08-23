(ns battle-asserts.issues.squares-and-cubes
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that takes an array of two numbers and checks
                  if the square root of the first number is equal to the cube root of the second number.")

(def signature
  {:input [{:argument-name "first-num" :type {:name "integer"}}
           {:argument-name "second-num" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer
             gen/small-integer))

(def test-data
  [{:expected true :arguments [4 8]}
   {:expected false :arguments [16 48]}
   {:expected true :arguments [9 27]}
   {:expected false :arguments [4 27]}])

(defn solution [first-num second-num]
  (= (Math/sqrt first-num) (Math/cbrt second-num)))
