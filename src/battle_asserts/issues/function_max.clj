(ns battle-asserts.issues.function-max
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "Implement the calculation function f(x) by the formula: `f(x) = x^2 for -2 <= x <2; x^2 + 4x + 5 for x >= 2; 4 for x < -2.` Using this function for n given numbers, calculate f(x). Among the calculated values, find the biggest."
   :ru "Создайте функцию, которая рассчитывается по формуле: `f(x) = x^2 для -2 <= x <2; x^2 + 4x + 5 для x >= 2; 4 для x < -2.` Примените эту функкцию к целочисленному массиву и найдите наибольшее число."})

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple (gen/choose -10 10))))

(def test-data
  [{:expected 4
    :arguments [[1 -2 0 -1]]}
   {:expected 362
    :arguments [[3 17 11 10 5]]}
   {:expected 577
    :arguments [[6 -12 22]]}
   {:expected 4
    :arguments [[-1 -2 -100 0]]}])

(defn solution [arr]
  (letfn [(calc-fun [x] (cond (and (>= x -2) (< x 2)) (* x x)
                              (>= x 2) (+ (* x x) (* 4 x) 5)
                              :else 4))]
    (apply max (map calc-fun arr))))
