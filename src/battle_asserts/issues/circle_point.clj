(ns battle-asserts.issues.circle-point
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "On the flat there is a circle centered at zero point (0, 0)
                  with radius R. Does the point with coordinates X and Y belongs to this circle.
                  Tip: to solve the problem, you need to find the hypotenuse.")

(def signature
  {:input  [{:argument-name "X" :type {:name "integer"}}
            {:argument-name "Y" :type {:name "integer"}}
            {:argument-name "R" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -50 50) (gen/choose -50 50) (gen/choose 1 100)))

(def test-data
  [{:expected false
    :arguments [10 3 5]}
   {:expected true
    :arguments [-50 0 100]}
   {:expected true
    :arguments [-1 2 5]}
   {:expected true
    :arguments [-50 50 100]}
   {:expected false
    :arguments [-1 3 1]}])

(defn solution [x y radius]
  (let [hypotenuse  (Math/sqrt (+ (* x x)
                                  (* y y)))] (<= hypotenuse radius)))

