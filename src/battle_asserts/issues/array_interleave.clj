(ns battle-asserts.issues.array-interleave
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function, which interleave array with another array.
                 Examlple:
                 Input: [1 3 5 ] [2 4 6]
                 Output: [1 2 3 4 5 6]")

(defn arguments-generator []
  (gen/tuple (gen/list gen/int) (gen/list gen/int)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [array-first array-second]
  (->>
   array-first
   (map-indexed #(vector %2 (nth array-second %1 nil)))
   flatten
   (filter #(not (nil? %)))
   (take (* 2 (min (count array-first) (count array-second))))))

