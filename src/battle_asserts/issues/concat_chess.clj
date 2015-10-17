(ns battle-asserts.issues.concat-chess
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function that concatenate 2 strings by characters, one by one.")

(defn arguments-generator []
  (let [words (faker/words {:lang :en :n 20})]
    (gen/tuple (gen/elements words)
               (gen/elements words))))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [str1 str2]
  (let [s1 (seq str1)
        s2 (seq str2)
        maxcount (apply max (map count [s1 s2]))]
    (-> (map list
             (take maxcount (concat s1 (repeat nil)))
             (take maxcount (concat s2 (repeat nil))))
        flatten
        clojure.string/join)))
