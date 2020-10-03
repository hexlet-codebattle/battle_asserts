(ns battle-asserts.issues.sort-by-length
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Create a function that returns an array of strings sorted by length in descending order.")

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (faker/words {:lang :en :n 100})) 3 10)))

(def test-data
  [{:expected ["dddd" "ccc" "bb" "a"] :arguments [["a" "ccc" "dddd" "bb"]]}
   {:expected ["shortcake" "apple" "pie"] :arguments [["apple" "pie" "shortcake"]]}
   {:expected ["september" "august" "april" "may"] :arguments [["may" "april" "september" "august"]]}])

(defn solution [words]
  (vec (sort-by count > words)))
