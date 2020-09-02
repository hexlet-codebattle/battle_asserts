(ns battle-asserts.issues.star-number
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that takes a positive integer and returns the nth \"star number\".
                  A star number is a centered figurate number a centered hexagram (six-pointed star), such as the one that Chinese checkers is played on.
                  Note: https://en.wikipedia.org/wiki/Star_number")

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer))

(def test-data
  [{:expected 13 :arguments [2]}
   {:expected 37 :arguments [3]}
   {:expected 121 :arguments [5]}])

(defn solution [num]
  (inc (* 6 num (dec num))))
