(ns battle-asserts.issues.next-greater-element
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array, return the Next Greater Element (NGE) for every element.
                 The Next greater Element for an element x is the first greater element on the right side of x in array.
                 Elements for which no greater element exist, consider next greater element as -1.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int)))

(defn max-than [value in]
  (first (filter #(< value %) in)))

(defn solution [array]
  (map-indexed #(or
                 (max-than %2 (subvec array (inc %1)))
                 -1)
               array))
