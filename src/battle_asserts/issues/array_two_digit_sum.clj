(ns battle-asserts.issues.array-two-digit-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["collections"])

(def description "Calculate digits sum of two-digit integers in array.
                  After that, calculate whole sum of produced array.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 10 99) 3 10)))

(def test-data
  [{:expected 6 :arguments [[10 11 12]]}
   {:expected 20 :arguments [[11 22 33 44]]}
   {:expected 108 :arguments [[99 99 99 99 99 99]]}])

(defn two-digit-sum [n]
  (let [first-num (/ n 10)
        second-num (rem n 10)]
    (int (+ first-num second-num))))

(defn solution [numbers]
  (let [two-number-arr (map two-digit-sum numbers)]
    (apply + two-number-arr)))
