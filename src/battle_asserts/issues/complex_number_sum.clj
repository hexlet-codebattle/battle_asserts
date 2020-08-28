(ns battle-asserts.issues.complex-number-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Implement the function that calculates sum of two complex numbers.")

(def signature
  {:input  [{:argument-name "first-num" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second-num" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected "4-3i"
    :arguments [[1 -2], [3 -1]]}
   {:expected "-1+3i"
    :arguments [[-3 2], [2 1]]}
   {:expected "5+3i"
    :arguments [[3 2], [2 1]]}
   {:expected "-5-3i"
    :arguments [[-3 -2], [-2 -1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [pair] (+ (first pair) (last pair)))
          (imaginary-part [pair] (+ (first pair) (last pair)))
          (format-imaginary [num] (if (>= num 0) (str "+" num) num))]
    (str (real-part [real1 real2]) (format-imaginary (imaginary-part [img1 img2])) "i")))
