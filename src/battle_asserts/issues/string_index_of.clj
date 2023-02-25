(ns battle-asserts.issues.string-index-of
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Create a function  which takes 2 parameters (`first` and `second`) and locates the first occurrence of the `second` in the `first` and returns it's position, -1 if not found."
   :ru "Создайте функцию, которая принимает две строки и верните индекс первого вхождения строки `second` в строку `first`, в ином случае верните -1."})

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
  (let [occurence (s/index-of first-str second-str)]
    (if (nil? occurence)
      -1
      occurence)))
