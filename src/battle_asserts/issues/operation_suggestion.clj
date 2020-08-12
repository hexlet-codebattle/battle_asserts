(ns battle-asserts.issues.operation-suggestion
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Write a function that
                  takes three numbers (first operand, second operand and result)
                  and return string if they should be added, subtracted, multiplied or divided to get result.")

(def signature
  {:input [{:argument-name "first-num" :type {:name "integer"}}
           {:argument-name "second-num" :type {:name "integer"}}
           {:argument-name "result" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(def operations-map
  {"+" (fn [a b] (+ a b))
   "-" (fn [a b] (- a b))
   "*" (fn [a b] (* a b))
   "/" (fn [a b] (/ a b))})

(defn arguments-generator []
  (gen/tuple (gen/choose -50 50) (gen/one-of [(gen/choose -50 -1) (gen/choose 1 50)]) (gen/choose -50 50)))

(def test-data
  [{:arguments [10 12 22]
    :expected "addition"}
   {:arguments [10 5 5]
    :expected "substraction"}
   {:arguments [10 2 20]
    :expected "multiply"}
   {:arguments [33 3 11]
    :expected "division"}
   {:arguments [33 33 33]
    :expected "unrecognized operation!!"}])

(defn solution [first-num second-num result]
  (cond
    (= ((operations-map "+") first-num second-num) result) "addition"
    (= ((operations-map "-") first-num second-num) result) "substraction"
    (= ((operations-map "*") first-num second-num) result) "multiply"
    (= ((operations-map "/") first-num second-num) result) "division"
    :else "unrecognized operation!!"))
