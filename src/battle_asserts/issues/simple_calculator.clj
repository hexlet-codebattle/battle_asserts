(ns battle-asserts.issues.simple-calculator
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given two **integer** numbers. Create a simple calculator that supports next operations: add, substract, divide, multiply, modulo. Use floor rounding for division operations.")

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}
           {:argument-name "operation" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [operations ["+" "-" "*" "/" "%"]]
    (gen/tuple (gen/choose -50 50) (gen/one-of [(gen/choose 1 50) (gen/choose -50 -1)]) (gen/elements operations))))

(def test-data
  [{:expected 11 :arguments [1 10 "+"]}
   {:expected -3 :arguments [2 5 "-"]}
   {:expected 0 :arguments [0 5 "*"]}
   {:expected 5 :arguments [10 2 "/"]}
   {:expected 2 :arguments [2 3 "%"]}])

(defn solution
  [first-num second-num operation]
  (let [operations-map {"+" (fn [a b] (+ a b))
                        "-" (fn [a b] (- a b))
                        "*" (fn [a b] (* a b))
                        "/" (fn [a b] (int (/ a b)))
                        "%" (fn [a b] (mod a b))}]
    ((operations-map operation) first-num second-num)))
