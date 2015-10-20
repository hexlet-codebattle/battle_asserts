(ns battle-asserts.issues.pascals-triangle
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Write a method that returns the nth row of Pascal's Triangle.")

(defn arguments-generator []
  (gen/tuple gen/pos-int))

(def test-data
  [
   {:expected [1 3 3 1]
    :arguments [3]}
   {:expected [1]
    :arguments [0]}
   {:expected [1 1]
    :arguments [1]}
   {:expected [1 2 1]
    :arguments [2]}
   {:expected [1 4 6 4 1]
    :arguments [4]}])

(defn solution [n]
  (vec (nth
        (iterate #(concat [1]
                          (map + % (rest %)) [1])
                 [1])
        n)))

