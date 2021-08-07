(ns battle-asserts.issues.operation-suggestion
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["games" "math"])

(def description
  {:en "Write a function that takes three numbers (first operand, second operand and result) and return string if they should be added, subtracted, multiplied or divided to get result."
   :ru "Создайте функцию, которая принимает три числа (первый операнд, второй операнд и результат) и возвращает строку, если они должны быть добавлены, вычтены, умножены или разделены для получения результата. Результирующая операция должна быть на английском языке."})

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}
           {:argument-name "result" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(def operations-map
  {"+" +
   "-" -
   "*" *
   "/" (fn [a b] (/ a b))})

(defn arguments-generator []
  (let [first-num (gen/generate (gen/choose -50 50))
        second-num (gen/generate (gen/one-of [(gen/choose -50 -1) (gen/choose 1 50)]))
        result [(+ first-num second-num) (- first-num second-num) (* first-num second-num) (/ first-num second-num) second-num]]
    (gen/tuple (gen/return first-num) (gen/return second-num) (gen/elements result))))

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
