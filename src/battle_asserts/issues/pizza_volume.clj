(ns battle-asserts.issues.pizza-volume
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Implement a function that calculates the volume of pizza. Round final result.")

(def signature
  {:input [{:argument-name "radius" :type {:name "integer"}}
           {:argument-name "height" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 50) (gen/choose 1 50)))

(def test-data
  [{:expected 3 :arguments [1 1]}
   {:expected 308 :arguments [7 2]}
   {:expected 942 :arguments [10 3]}])

(defn solution [radius height]
  (int (Math/round (* radius radius height Math/PI))))
