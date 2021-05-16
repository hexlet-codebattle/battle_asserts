(ns battle-asserts.issues.number-to-english
  (:require [clojure.test.check.generators :as gen]
            [clojure.pprint :as pp]))

(def level :hard)

(def tags ["strings"])

(def description
  {:en "Write a function that accepts a positive integer between 0 and 2000 inclusive and returns a string representation of that integer written in English."
   :ru "Напишите функцию, которая принимает положительное целое число между 0 и 2000 включительно, и возвращает строковое представление этого числа на английском языке."})

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 2000)))

(def test-data
  [{:expected "Zero" :arguments [0]}
   {:expected "Eleven" :arguments [11]}
   {:expected "Fifty-nine" :arguments [59]}
   {:expected "One hundred twenty-nine" :arguments [129]}
   {:expected "One thousand, nine hundred twenty-three" :arguments [1923]}])

(defn solution [num]
  (pp/cl-format nil "~@(~@[~R~]~^ ~A.~)" num))
