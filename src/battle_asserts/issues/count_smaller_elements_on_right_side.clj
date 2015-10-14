(ns battle-asserts.issues.count-smaller-elements-on-right-side
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function to count number of smaller elements on right of each element in an array.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int)))

(defn count-smaller [value array]
  (count (filter #(> value %) array)))

(defn solution [array]
  (map-indexed #(count-smaller %2 (subvec array (inc %1))) array))
