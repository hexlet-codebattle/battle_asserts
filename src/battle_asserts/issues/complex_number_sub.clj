(ns battle-asserts.issues.complex-number-sub
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Implement a function that calculates difference of two complex numbers.
                  Return result as array where first number is real part and second number is imaginary part.")

(def signature
  {:input  [{:argument-name "first" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected [2 -1] :arguments [[5 -2] [3 -1]]}
   {:expected [-5 2] :arguments [[-3 3] [2 1]]}
   {:expected [1 3] :arguments [[3 4] [2 1]]}
   {:expected [-1 -3] :arguments [[-3 -4] [-2 -1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [pair] (- (first pair) (last pair)))
          (imaginary-part [pair] (- (first pair) (last pair)))]
    [(real-part [real1 real2]) (imaginary-part [img1 img2])]))
