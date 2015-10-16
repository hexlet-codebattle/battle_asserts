(ns battle-asserts.issues.more-than-medium
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write a function that will return the words,
                 which are longer than the average length of all the words.")

(defn arguments-generator []
  (let [sentences (repeatedly 30 #(faker/sentence {:words-range [1 10]}))]
    (gen/tuple (gen/elements sentences))))

(defn solution [string]
  (let [words (re-seq #"\w+" string)
        average (/ (reduce + (map #(count %)
                                  words))
                   (count words))]

    (vec
     (filter #(> (count %) average)
             words))))
