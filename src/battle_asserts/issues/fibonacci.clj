(ns battle-asserts.issues.fibonacci
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a method that handles Fibonacci sequences.
                 Have it return the nth item in the Fibonacci sequence.
                 Hint: The first item in the sequence is 0.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 20)))

(defn solution [number]
  (condp = number
    0 0
    1 1
    (+
     (solution (- number 1))
     (solution (- number 2)))))
