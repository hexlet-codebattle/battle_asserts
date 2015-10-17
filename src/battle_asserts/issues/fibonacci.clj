(ns battle-asserts.issues.fibonacci
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a method that handles Fibonacci sequences.
                 Have it return the nth item in the Fibonacci sequence.
                 Hint: The first item in the sequence is 0.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 20)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [number]
  (condp = number
    0 0
    1 1
    (+
     (solution (- number 1))
     (solution (- number 2)))))
