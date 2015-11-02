(ns battle-asserts.issues.isomorphic-strings
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given two strings, determine if they are isomorphic.
                 Two strings are isomorphic if the characters in s can be replaced to get t.
                 All occurrences of a character must be replaced with another character while preserving the order of characters.
                 No two characters may map to the same character but a character may map to itself.")

(defn arguments-generator []
  (letfn [(two-string []
            (let [strings (faker/words {:lang :en :n 2})
                  min-length (apply min (map count strings))]
              (map #(subs % 0 min-length) strings)))
          (two-isomorphic-string []
                                 (let [first-string (faker/word {:lang :en})
                                       alphabet (map char (range (int \a) (inc (int \z))))
                                       mapping (zipmap (distinct first-string) (shuffle alphabet))
                                       second-string (s/join (map mapping first-string))]
                                   [first-string second-string]))]
    (gen/one-of [(gen/elements (repeatedly 30 two-string))
                 (gen/elements (repeatedly 30 two-isomorphic-string))])))

(def test-data
  [{:expected true
    :arguments ["egg" "add"]}
   {:expected false
    :arguments ["foo" "bar"]}
   {:expected true
    :arguments ["paper" "title"]}
   {:expected true
    :arguments ["ca" "ab"]}
   {:expected true
    :arguments ["aa" "bb"]}
   {:expected true
    :arguments ["aedor" "eiruw"]}])

(defn process [string]
  (let [alphabet (distinct string)]
    (map #(.indexOf alphabet %) string)))

(defn solution [s, t]
  (= (process s) (process t)))
