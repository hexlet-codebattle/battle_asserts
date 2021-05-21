(ns battle-asserts.issues.repdigit-number
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "A repdigit is a positive number composed out of the same digit.
        Create a function that checks an integer it's a repdigit or not."
   :ru "Однообразное число (репдиджит) - это положительное число, составленное из одной и той же цифры.
        Создайте функцию, которая проверяет является ли переданное число репдиджитом."})

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [repdigits (gen/elements [11 111 1111 22 222 2222 33 333 3333 3333 44 444 444 4444])]
    (gen/tuple (gen/one-of [(gen/choose 10 1000) repdigits]))))

(def test-data
  [{:expected true :arguments [66]}
   {:expected true :arguments [111]}
   {:expected false :arguments [112]}
   {:expected false :arguments [10]}])

(defn solution [number]
  (let [stringified (str number)
        start-number (first stringified)
        filtered-num (s/join "" (filter (fn [ch] (= ch start-number)) stringified))]
    (= stringified filtered-num)))
