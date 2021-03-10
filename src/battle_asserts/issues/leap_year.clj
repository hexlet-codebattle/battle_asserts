(ns battle-asserts.issues.leap-year
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Determine if the year is a leap year.
                  Leap years are all divisible by 4,
                  with the exception of centuries, which are not divisible by 400.")

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
