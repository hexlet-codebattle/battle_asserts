(ns battle-asserts.issues.more-than-medium
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def disabled true)

(def description "Given a sentence (as string),
                  return an array of words which are longer than the average length of all the words.
                  Words a separated by a whitespace. If there is a trailing period (dot), it should be omittied.")

(def signature
  {:input  [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [sentences (repeatedly 50 #(faker/sentence {:words-range [2 6]}))]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected ["This" "sample" "string"]
    :arguments ["This is a sample string"]}
   {:expected ["another" "sample"]
    :arguments ["Some another sample"]}])

(defn solution [string]
  (let [words (re-seq #"\w+" string)
        average (/ (reduce + (map count
                                  words))
                   (count words))]

    (filterv #(> (count %) average)
             words)))
