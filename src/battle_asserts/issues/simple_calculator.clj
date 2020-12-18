(ns battle-asserts.issues.simple-calculator
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given two **integer** numbers. Create a simple calculator that supports next operations: `add`, `substract`, `pow`, `multiply`, `remainder`.")

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}
           {:argument-name "operation" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [operations ["+" "-" "*" "^" "%"]]
    (gen/tuple (gen/choose 6 20) (gen/choose 1 5) (gen/elements operations))))

(def test-data
  [{:expected 11 :arguments [1 10 "+"]}
   {:expected -3 :arguments [2 5 "-"]}
   {:expected -5 :arguments [-1 5 "*"]}
   {:expected 5 :arguments [1 5 "*"]}
   {:expected 100 :arguments [10 2 "^"]}
   {:expected 100 :arguments [-10 2 "^"]}
   {:expected -1 :arguments [-19 2 "%"]}])

(defn solution
  [first-num second-num operation]
  (let [operations-map {"+" (fn [a b] (+ a b))
                        "-" (fn [a b] (- a b))
                        "*" (fn [a b] (* a b))
                        "^" (fn [a b] (int (Math/pow a b)))
                        "%" (fn [a b] (rem a b))}]
    ((operations-map operation) first-num second-num)))
