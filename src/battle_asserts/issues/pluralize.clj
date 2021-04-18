(ns battle-asserts.issues.pluralize
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as f]))

(def level :easy)

(def tags ["collections" "strings"])

(def description
  {:en "Given a list of words in the singular form, return a set of those words in the plural form if they appear more than once in the list. There is no edge cases. Keep words order same."
   :ru "В переданном списке слов найдите повторяющиеся и исправьте их на множественное число, используя базовые правила английского языка. Сохраните порядок слов."})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [words (f/words {:n 25})
        repeat-words (repeatedly 10 #(rand-nth words))
        final-words (concat words repeat-words)]
    (gen/tuple (gen/vector (gen/elements final-words) 3 15))))

(def test-data
  [{:expected ["cows" "pig"] :arguments [["cow" "pig" "cow" "cow"]]}
   {:expected ["chairs"] :arguments [["chair" "chair" "chair"]]}
   {:expected ["chair" "pencil" "arm"] :arguments [["chair" "pencil" "arm"]]}
   {:expected ["clojure" "ruby" "rails" "elixir"] :arguments [["clojure" "ruby" "rail" "rail" "elixir"]]}])

(defn solution [words]
  (let [freq (frequencies words)
        uniq (distinct words)]
    (mapv (fn [word] (if (> (freq word) 1) (str word "s") word))
          uniq)))
