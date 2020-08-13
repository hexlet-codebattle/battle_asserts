(ns battle-asserts.issues.complex-number-sub
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Implement a function that calculates difference of two complex numbers.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected "2-1i"
    :arguments [[5 -2], [3 -1]]}
   {:expected "-5+2i"
    :arguments [[-3 3], [2 1]]}
   {:expected "1+3i"
    :arguments [[3 4], [2 1]]}
   {:expected "-1-3i"
    :arguments [[-3 -4], [-2 -1]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [pair] (- (first pair) (last pair)))
          (imaginary-part [pair] (- (first pair) (last pair)))
          (format-imaginary [num] (if (>= num 0) (str "+" num) num))]
    (str (real-part [real1 real2]) (format-imaginary (imaginary-part [img1 img2])) "i")))
