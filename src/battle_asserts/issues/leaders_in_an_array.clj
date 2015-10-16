(ns battle-asserts.issues.leaders-in-an-array
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a program to print all the LEADERS in the array.
                 An element is leader if it is greater than all the elements to its right side.
                 And the rightmost element is always a leader. For example int the array [16 17 4 3 5 2],
                 leaders are 17, 5 and 2.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int)))

(defn first-is-biggest [[head & tail]]
  (every? #(> head %) tail))

(defn solution [array]
  (->>
   array
   (map-indexed vector)
   (filter
    #(first-is-biggest (subvec array (first %))))
   (map second)))
