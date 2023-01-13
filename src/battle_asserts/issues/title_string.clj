(ns battle-asserts.issues.title-string
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["collections" "strings"])

(def description
  {:en "Check if a string `title` is a title string or not. A title string is one which has all the words in the string start with an upper case letter."
   :ru "Проверьте, начинаются ли все слова в строке с заглавной буквы."})

(def signature
  {:input [{:argument-name "title" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn collect-words []
  (let [words-count (gen/generate (gen/choose 5 15))
        words (faker/words {:n words-count})
        upper-cased (map s/capitalize words)
        result (gen/generate (gen/elements [words upper-cased]))]
    (str (s/join " " result) (gen/generate (gen/elements ["!" "?" "." "..." "?!"])))))

(defn arguments-generator []
  (gen/tuple (gen/elements (repeatedly 30 collect-words))))

(def test-data
  [{:expected false :arguments ["There are three types of zeros in JS!"]}
   {:expected true :arguments ["Learn Clojure Be Happy!"]}
   {:expected false :arguments ["Learn Ruby win tournaments?!"]}
   {:expected true :arguments ["Simple Title."]}])

(defn upper-case? [word]
  (= (s/capitalize word) word))

(defn solution [title]
  (let [splitted (s/split title #" ")]
    (= splitted (filterv upper-case? splitted))))
