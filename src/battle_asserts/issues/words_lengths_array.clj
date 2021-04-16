(ns battle-asserts.issues.words-lengths-array
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["collections" "strings"])

(def description
  {:en "Create a function that takes an array of words and transforms it into an array of each word's length."
   :ru "Создайте функцию, которая трансформирует массив слов в массив длин этих слов."})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (faker/words {:n 20})) 5 20)))

(def test-data
  [{:expected [5 5] :arguments [["hello" "world"]]}
   {:expected [4 4 4 7] :arguments [["some" "test" "data" "strings"]]}
   {:expected [7] :arguments [["clojure"]]}])

(defn solution [words]
  (mapv count words))
