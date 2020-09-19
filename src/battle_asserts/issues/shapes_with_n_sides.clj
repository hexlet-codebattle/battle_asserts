(ns battle-asserts.issues.shapes-with-n-sides
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Bob answers 'Sure.' if you ask him a question.
                 He answers 'Whoa, chill out!' if you yell at him.
                 He says 'Fine. Be that way!' if you address him without actually saying anything.
                 He answers 'Whatever.' to anything else.  ")

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
