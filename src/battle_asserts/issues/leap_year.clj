(ns battle-asserts.issues.leap-year
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["date-time"])

(def description
  {:en "Determine if the year is a leap year. Leap years are all divisible by 4, a year that is evenly divisible by 100 (for example, 1900) is a leap year only if it is also evenly divisible by 400."
   :ru "Определите, является ли год високосным. Любой год, который делится на 4 без остатка, является високосным годом. Тем не менее, есть еще небольшая особенность, которая должна быть учтена например, григорианский календарь предусматривает, что год, который делится без остатка на 100 является високосным годом только в том случае, если он также без остатка делится на 400."})

(def signature
  {:input [{:argument-name "year" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1800 2100)))

(def test-data
  [{:expected true :arguments [2012]}
   {:expected false :arguments [1913]}
   {:expected true :arguments [1804]}
   {:expected true :arguments [2000]}
   {:expected false :arguments [2100]}
   {:expected true :arguments [2020]}])

(defn solution [year]
  (letfn [(divisible? [a b] (zero? (mod a b)))]
    (and (divisible? year 4) (or (not (divisible? year 100)) (divisible? year 400)))))
