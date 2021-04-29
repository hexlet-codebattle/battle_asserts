(ns battle-asserts.issues.shapes-with-n-sides
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Create a function that takes a whole number as input and returns the shape with that number's amount of sides. Check examples below."
   :ru "Создайте функцию которая принимает целое число и возвращает строку с названием фигуры, состоящий из переданного количество сторон."})

(def signature
  {:input [{:argument-name "sides" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 10)))

(def test-data
  [{:expected "circle" :arguments [1]}
   {:expected "semi-circle" :arguments [2]}
   {:expected "triangle" :arguments [3]}
   {:expected "square" :arguments [4]}
   {:expected "pentagon" :arguments [5]}
   {:expected "hexagon" :arguments [6]}
   {:expected "heptagon" :arguments [7]}
   {:expected "octagon" :arguments [8]}
   {:expected "nonagon" :arguments [9]}
   {:expected "decagon" :arguments [10]}])

(def shapes
  ["circle"
   "semi-circle"
   "triangle"
   "square"
   "pentagon"
   "hexagon"
   "heptagon"
   "octagon"
   "nonagon"
   "decagon"])

(defn solution [sides-number]
  (shapes (dec sides-number)))
