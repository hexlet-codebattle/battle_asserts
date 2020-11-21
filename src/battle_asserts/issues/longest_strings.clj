(ns battle-asserts.issues.longest-strings
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given an array of strings, write the function, that return another array, containing all of its longest strings.")

(def signature
  {:input  [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (faker/words {:n 20})) 5 15)))

(def test-data
  [{:expected ["programms"]
    :arguments [["in" "Soviet" "Russia" "frontend" "programms" "you"]]}
   {:expected ["clojure" "greater"]
    :arguments [["using" "clojure" "makes" "your" "life" "greater"]]}
   {:expected ["a" "b" "c" "d"]
    :arguments [["a" "b" "c" "d"]]}])

(defn solution [words]
  (let [sorted-words (sort-by count words)
        longest-word (count (last sorted-words))]
    (filterv #(= (count %) longest-word) words)))
