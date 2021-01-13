(ns battle-asserts.issues.title-string
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def description "Check if a string `title` is a title string or not. A title string is one which has all the words in the string start with a upper case letter.")

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
  (gen/tuple (gen/elements (repeatedly 10 collect-words))))

(def test-data
  [{:expected false :arguments ["There are three types of zeros in JS!"]}
   {:expected true :arguments ["Learn Clojure Be Happy!"]}
   {:expected false :arguments ["Learn Ruby win tournaments?!"]}
   {:expected true :arguments ["Simple Title."]}])

(defn upper-case? [word]
  (let [first-chr (first word)]
    (= (s/upper-case first-chr) (str first-chr))))

(defn solution [title]
  (let [splitted (s/split title #" ")]
    (= splitted (filterv upper-case? splitted))))
