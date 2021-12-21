(ns battle-asserts.issues.word-wave
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as fk]
            [clojure.string :as s]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "Write a function that 'waves' through the word, i.e., changes the case to upper case for each next letter.
        And return the resulting word array."
   :ru "Напишите функцию, которая 'запускает волну' по слову, т.е. меняет регистр на верхний у каждой следующей буквы.
        И верните полученный массив слов."})

(def signature
  {:input [{:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [words (fk/words {:n 20})]
    (gen/tuple (gen/elements words))))

(def test-data
  [{:expected ["Ruby" "rUby" "ruBy" "rubY"] :arguments ["ruby"]}
   {:expected ["Clojure" "cLojure" "clOjure" "cloJure" "clojUre" "clojuRe" "clojurE"] :arguments ["clojure"]}])

(defn- capitalize [word position]
  (s/join
   (map-indexed #(if (= %1 position) (s/upper-case %2) %2) word)))

(defn- solution [word]
  (let [length (count word)]
    (mapv #(capitalize word %1) (range length))))
