(ns battle-asserts.issues.capitalized-words-number
  (:require [clojure.test.check.generators :as gen]
            [miner.strgen :as sg]
            [clojure.string :as str]))

(def level :easy)

(def description "Find number of words starting with capital letter")

(defn signature []
  {:input [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (sg/string-generator #"[A-Za-z]{1,8}") (gen/elements (range 1 9))))

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
  (->
   (str/split s #" ")
   (filter #(Character/isUpperCase %))
   (count)))
