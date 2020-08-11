(ns battle-asserts.issues.triangle-area
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Calculate area of triangle using Heron's Formula.
                  `s = (a + b + c) / 2`
                  `area = √(s * (s - a) * (s - b) * (s - c))
                  Use floor rounding for resulting area.")

(def signature
  {:input  [{:argument-name "a" :type {:name "integer"}}
            {:argument-name "b" :type {:name "integer"}}
            {:argument-name "c" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -30 30)  (gen/choose -30 30) (gen/choose -30 30)))

(def test-data
  [{:arguments [24 30 18]
    :expected 216}
   {:arguments [14 10 12]
    :expected 58}
   {:arguments [-14 -10 -12]
    :expected 58}])

(defn solution [a b c]
  (let [semi-perimeter (/ (+ a b c) 2)]
    (int
     (Math/sqrt
      (* semi-perimeter
         (- semi-perimeter a)
         (- semi-perimeter b)
         (- semi-perimeter c))))))
