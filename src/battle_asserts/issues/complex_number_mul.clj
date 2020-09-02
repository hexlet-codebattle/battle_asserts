(ns battle-asserts.issues.complex-number-mul
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Implement a function that multiply two complex numbers.
                  Return result as array where first number is real part and second number is imaginary part.")

(def signature
  {:input  [{:argument-name "first-num" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second-num" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected [1 -7] :arguments [[1 -2] [3 -1]]}
   {:expected [-8 1] :arguments [[-3 2] [2 1]]}
   {:expected [4 7] :arguments [[3 2] [2 1]]}
   {:expected [-5 -1] :arguments [[2 3] [-1 1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [first-pair second-pair] (- (* (first first-pair) (first second-pair)) (* (last first-pair) (last second-pair))))
          (imaginary-part [first-pair second-pair] (+ (* (first first-pair) (last second-pair)) (* (last first-pair) (first second-pair))))]
    [(real-part [real1 img1] [real2 img2]) (imaginary-part [real1 img1] [real2 img2])]))
