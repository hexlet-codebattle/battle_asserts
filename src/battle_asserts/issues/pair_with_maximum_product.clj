(ns battle-asserts.issues.pair-with-maximum-product
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array with both +ive and -ive integers, return a pair with highest product.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int 2 25)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn product [array]
  (apply * array))

(defn solution [array]
  (let [sort-array (sort array)
        two-max (take-last 2 sort-array)
        two-min (take 2 sort-array)]
    (if (>
         (product two-min)
         (product two-max))
      two-min
      two-max)))
