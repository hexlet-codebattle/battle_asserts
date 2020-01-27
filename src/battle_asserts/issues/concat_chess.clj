(ns battle-asserts.issues.concat-chess
  (:require [clojure.test.check.generators :as gen]
            [clojure.string]
            [faker.generate :as faker]))

(def level :easy)

(def description "Concatenate 2 strings by characters, one by one. In other words, take the 1st char from the first string, then 1st char from the second string, then 2nd char from the first string, then 2nd char from the second string, and so on. If one string ends before the other, just continue with the remaining characters.")

(def signature
  {:input [{:argument-name "str1" :type {:name "string"}}
           {:argument-name "str2" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [words (faker/words {:lang :en :n 20})]
    (gen/tuple (gen/elements words)
               (gen/elements words))))

(def test-data
  [{:expected "awbxcydz"
    :arguments ["abcd" "wxyz"]}
   {:expected "axbyczd"
    :arguments ["abcd" "xyz"]}
   {:expected "wdoorudbleword"
    :arguments ["word" "doubleword"]}
   {:expected "abcdefgh"
    :arguments ["aceg" "bdfh"]}])

(defn solution [str1 str2]
  (let [s1 (seq str1)
        s2 (seq str2)
        maxcount (apply max (map count [s1 s2]))]
    (-> (map list
             (take maxcount (concat s1 (repeat nil)))
             (take maxcount (concat s2 (repeat nil))))
        flatten
        clojure.string/join)))
