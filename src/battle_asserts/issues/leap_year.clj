(ns battle-asserts.issues.leap-year
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Determine if the year is a leap year.
                  Leap years are all divisible by 4,
                  with the exception of centuries, which are not divisible by 400.")

(def signature
  {:input [{:argument-name "year" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 0 3000)))

(def test-data
  [{:expected true
    :arguments [2012]}
   {:expected false
    :arguments [1913]}
   {:expected true
    :arguments [1804]}
   {:expected false
    :arguments [-1913]}
   {:expected true
    :arguments [2020]}])

(defn solution [year]
  (if (and (zero? (mod year 4)) (not (and (zero? (mod year 100)) (not= (mod year 400) 0)))) true false))