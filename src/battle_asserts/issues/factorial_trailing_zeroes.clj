(ns battle-asserts.issues.factorial-trailing-zeroes
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function, which return the number of trailing zeroes in n!.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 90)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn factorial [n]
  (apply *' (range 1 (+ n 1))))

(defn solution [n]
  (->>
   n
   factorial
   str
   (re-find #"0+$")
   count))
