(ns battle-asserts.issues.complex-number-mul
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Implement a function that multiply two complex numbers.")

(def signature
  {:input  [{:argument-name "first-num" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second-num" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected "1-7i"
    :arguments [[1 -2], [3 -1]]}
   {:expected "-8+1i"
    :arguments [[-3 2], [2 1]]}
   {:expected "4+7i"
    :arguments [[3 2], [2 1]]}
   {:expected "-5-1i"
    :arguments [[2 3], [-1 1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [first-pair second-pair] (- (* (first first-pair) (first second-pair)) (* (last first-pair) (last second-pair))))
          (imaginary-part [first-pair second-pair] (+ (* (first first-pair) (last second-pair)) (* (last first-pair) (first second-pair))))
          (format-imaginary [num] (if (>= num 0) (str "+" num) num))]
    (str (real-part [real1 img1] [real2 img2]) (format-imaginary (imaginary-part [real1 img1] [real2 img2])) "i")))
