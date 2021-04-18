(ns battle-asserts.issues.colour-invert
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training"])

(def description
  {:en "Create a function that inverts the RGB values of a given array."
   :ru "Создайте функцию, которая инвертирует RGB цвета в массиве."})

(def signature
  {:input [{:argument-name "colours" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 0 255) 3)))

(def test-data
  [{:expected [0 0 0] :arguments [[255 255 255]]}
   {:expected [255 255 255] :arguments [[0 0 0]]}
   {:expected [90 85 34] :arguments [[165 170 221]]}])

(defn solution [colours]
  (mapv #(- 255 %) colours))
