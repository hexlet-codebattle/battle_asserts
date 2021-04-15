(ns battle-asserts.issues.string-index-of
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as str]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description "Create a function
                  which takes 2 parameters (str1 and str2) and locates the first occurrence
                  of the str2 in the str1 and returns it's position, -1 if not found.")

(def signature
  {:input  [{:argument-name "first" :type {:name "string"}}
            {:argument-name "second" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [words (repeatedly 10 faker/word)]
    (gen/tuple (gen/elements words) (gen/elements words))))

(def test-data
  [{:expected 5
    :arguments ["some str" "st"]}
   {:expected -1
    :arguments ["some str" "11"]}
   {:expected 1
    :arguments ["some str" "ome"]}
   {:expected 0
    :arguments ["test" "t"]}])

(defn solution [first-str second-str]
  (let [occurence (str/index-of first-str second-str)]
    (if (nil? occurence)
      -1
      occurence)))
