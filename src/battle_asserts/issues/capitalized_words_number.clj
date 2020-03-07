(ns battle-asserts.issues.capitalized-words-number
  (:require [clojure.test.check.generators :as gen]
            [miner.strgen :as sg]
            [clojure.string :as str]))

(def level :easy)

(def description "Find number of words starting with capital letter")

(def signature
  {:input [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple
   (gen/let [words (gen/vector (sg/string-generator #"[A-Za-z]{1,8}") 0 9)]
     (gen/return (str/join " " words)))))

(def test-data
  [{:expected 0
    :arguments ["lorem ipsum."]}
   {:expected 2
    :arguments ["Lorem ipsum, Hello"]}
   {:expected 2
    :arguments ["lorem, ipsum Hello CaeSar"]}
   {:expected 0
    :arguments ["loRem ipSum"]}
   {:expected 3
    :arguments ["Lorem Ipsum Caesar"]}])

(defn solution [s]
  (->>
   (str/split s #" ")
   (filter #(when-let [letter (get % 0)] (Character/isUpperCase letter)))
   (count)))
