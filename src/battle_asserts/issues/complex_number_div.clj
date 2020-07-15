(ns battle-asserts.issues.complex-number-div
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Implement a function that divide two complex numbers.
                  Result number should be formatted as follows `real+imgi` or `-real-imgi` and so on.
                  If number can't be computed, return Division by zero!11!1 .")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple gen/small-integer gen/small-integer)
             (gen/tuple gen/small-integer gen/small-integer)))

(def test-data
  [{:expected "0.50-0.50i"
    :arguments [[1 -2], [3 -1]]}
   {:expected "-0.80+1.40i"
    :arguments [[-3 2], [2 1]]}
   {:expected "1.60+0.20i"
    :arguments [[3 2], [2 1]]}
   {:expected "-2.50-0.50i"
    :arguments [[2 3], [-1 -1]]}
   {:expected "Division by zero!11!1"
    :arguments [[2 3], [0 0]]}])

(defn solution [[real1 img1] [real2 img2]]
  (letfn [(real-part [first-pair second-pair]
            (float (/ (+ (* (first first-pair) (first second-pair)) (* (last first-pair) (last second-pair)))
                      (+ (* (first second-pair) (first second-pair)) (* (last second-pair) (last second-pair))))))
          (imaginary-part [first-pair second-pair]
            (float (/ (- (* (last first-pair) (first second-pair)) (* (first first-pair) (last second-pair)))
                      (+ (* (first second-pair) (first second-pair)) (* (last second-pair) (last second-pair))))))
          (formatted-real [num] (format "%.2f" num))
          (formatted-imaginary [num] (if (pos? num) (format "+%.2fi"  num) (format "%.2fi" num)))]
    (try
      (let
       [real (formatted-real (real-part [real1 img1] [real2 img2]))
        img (formatted-imaginary (imaginary-part [real1 img1] [real2 img2]))]
        (str real img))
      (catch ArithmeticException e "Division by zero!11!1"))))