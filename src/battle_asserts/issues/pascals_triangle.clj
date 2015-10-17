(ns battle-asserts.issues.pascals-triangle
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Write a method that will return the nth row of Pascal's Triangle.")

(defn arguments-generator []
  (gen/tuple gen/pos-int))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [n]
  (vec (nth
        (iterate #(concat [1]
                          (map + % (rest %)) [1])
                 [1])
        n)))
