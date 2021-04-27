(ns battle-asserts.issues.create-picture
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :easy)

(def tags ["collections" "strings"])

(def description
  {:en "Given an array of words, write a function, that return picture (array) with borders made from asterisks `*` using longest words."
   :ru "Дан массив слов, напишите функцию, которая возвращает картинку (массив) с границей из звездочек `*`, внутри границы находятся самые длинные слова."})

(def signature
  {:input  [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (faker/words {:n 20})) 2 15)))

(def test-data
  [{:expected ["*****"
               "*def*"
               "*****"]
    :arguments [["a" "bc" "def"]]}
   {:expected ["******"
               "*some*"
               "*word*"
               "******"]
    :arguments [["some" "word" "foo" "bar"]]}])

(defn solution [words]
  (let [longest-word-size (count (last (sort-by count words)))
        picture-words (filter #(= (count %) longest-word-size) words)
        picturized (map #(str "*" % "*") picture-words)
        border (s/join (repeat (+ 2 longest-word-size) "*"))]
    (conj (apply conj [border] picturized) border)))
