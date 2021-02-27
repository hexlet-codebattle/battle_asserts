(ns battle-asserts.issues.boxes-volume
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description
  {:en "Given an array of boxes, create a function that returns the total volume of all those boxes combined together. A box is represented by an array with three elements: length, width and height. Each box will always have three dimensions."})

(def signature
  {:input [{:argument-name "boxes" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [length (gen/generate (gen/choose 1 8))
        limit (+ length 2)]
    (gen/tuple (gen/vector (gen/vector (gen/choose 1 30) 3) length limit))))

(def test-data
  [{:expected 266 :arguments [[[2 3 2] [6 6 7] [1 2 1]]]}
   {:expected 10 :arguments [[[2 2 2] [2 1 1]]]}
   {:expected 8 :arguments [[[2 2 2]]]}])

(defn solution [boxes]
  (reduce (fn [acc elem] (+ acc (reduce * elem))) 0 boxes))
