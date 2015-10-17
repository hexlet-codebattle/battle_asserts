(ns battle-asserts.issues.drop-every-n-element
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Drop every N'th element from a list.")

(defn arguments-generator []
  (gen/tuple (gen/such-that #(not (zero? %)) gen/pos-int)
             (gen/vector gen/int)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [n coll]
  (keep-indexed #(if-not (= (mod (inc %1) n)
                            0) %2)
                coll))
