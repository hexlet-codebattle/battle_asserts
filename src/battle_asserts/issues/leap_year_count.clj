(ns battle-asserts.issues.leap-year-count
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given a range of years as array of two numbers, return the number of leap years there are within the range (inclusive).
                  Leap years are all divisible by 4,
                  with the exception of centuries, which are not divisible by 400.")

(def signature
  {:input [{:argument-name "years" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [first-year (gen/generate (gen/choose 1800 2300))
        second-year (+ first-year (gen/generate (gen/choose 4 200)))]
    (letfn [(years-pair [] [first-year second-year])]
      (gen/tuple (gen/elements [(years-pair) (years-pair) (years-pair)])))))

(def test-data
  [{:expected 2 :arguments [[1980 1984]]}
   {:expected 7 :arguments [[2000 2025]]}
   {:expected 49 :arguments [[1800 2000]]}])

(defn leap-year? [year]
  (letfn [(divisible? [a b] (zero? (mod a b)))]
    (and (divisible? year 4) (or (not (divisible? year 100)) (divisible? year 400)))))

(defn solution [[left-border right-border]]
  (count (filter leap-year? (range left-border (inc right-border)))))