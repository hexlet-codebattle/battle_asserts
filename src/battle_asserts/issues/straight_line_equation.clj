(ns battle-asserts.issues.straight-line-equation
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def disabled true)

(def description "Create a function to describe a line passing through
                  two points with coordinates (x1, y1) and (x2, y2).
                  Return result as array where first number is `kx` and second number is `b`. Use floor rounding.")

(def signature
  {:input  [{:argument-name "first" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "second" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator
  []
  (gen/tuple (gen/tuple (gen/choose -10 10) (gen/choose -10 10))
             (gen/tuple (gen/choose -10 10) (gen/choose -10 10))))

(def test-data
  [{:expected [1 3] :arguments [[6 9] [1 4]]}
   {:expected [0 -2] :arguments [[6 0] [-1 -3]]}
   {:expected [-5 10] :arguments [[2 0] [0 10]]}
   {:expected [5 0] :arguments [[1 5] [0 0]]}])

(defn solution [[x1, y1], [x2, y2]]
  (try
    (letfn [(k [x1, x2, y1, y2] (float (/ (- y1 y2) (- x1 x2))))]
      (let [first-part (int (k x1 x2 y1 y2))
            second-part (int (- y2 (* (k x1 x2 y1 y2) x2)))]
        [first-part second-part]))
    (catch ArithmeticException e [0 0])))
