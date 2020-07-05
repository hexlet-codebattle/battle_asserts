(ns battle-asserts.issues.circle-point
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Determine whether a given point belongs to a circle.
                  Tip: to solve the problem, you need to find the hypotenuse.")

(def signature
  {:input  [{:argument-name "x" :type {:name "integer"}}
            {:argument-name "y" :type {:name "integer"}}
            {:argument-name "r" :type {:name "integer"}}]
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
  (defn hypotenuse [x y]
    (Math/sqrt
     (+ (* x x)
        (* y y))))
  (<=  (hypotenuse x y) radius))

