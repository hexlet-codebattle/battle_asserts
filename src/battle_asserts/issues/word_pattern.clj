(ns battle-asserts.issues.word-pattern
  (:require [battle-asserts.utility :as util]
            [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["collections" "strings"])

(def description
  {:en "Given a pattern and a string `str`, find if `str` follows the same pattern. For example, pattern = \" abba \", str = \" dog cat cat dog \" should return `true`."
   :ru "Дан паттерн `pattern` и строка `str`. Паттерн это строка, в которой каждая буква представляет собой \"маску\" для соответствующего слова в строке. Если два разных слова в строке соответствуют одной и той же букве в паттерне, то эти слова должны быть одинаковыми. Проверьте, соответствует ли строка паттерну."})

(def signature
  {:input  [{:argument-name "pattern" :type {:name "string"}}
            {:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(pattern []
            (let [alphabet [\a \b \c \d \e \f \g]]
              (s/join (repeatedly (inc (rand-int 10)) #(rand-nth alphabet)))))
          (string []
            (s/join " " (faker/words {:lang :en :n (inc (rand-int 10))})))
          (string-by-pattern [pattern]
            (let [sym-in-pattern (distinct (seq pattern))
                  words (util/unique-words (count sym-in-pattern))
                  associations (zipmap sym-in-pattern words)]
              (s/join " " (map associations (seq pattern)))))]
    (gen/one-of [(gen/tuple (gen/elements (repeatedly 30 pattern))
                            (gen/elements (repeatedly 30 string)))
                 (gen/bind (gen/elements (repeatedly 30 pattern))
                           #(gen/tuple (gen/return %)
                                       (gen/return (string-by-pattern %))))])))

(def test-data
  [{:expected true
    :arguments ["zzbinqs" "dragonborn dragonborn by his honor in sworm"]}
   {:expected true
    :arguments ["abba" "dog cat cat dog"]}
   {:expected false
    :arguments ["abba" "dog cat cat fisth"]}
   {:expected true
    :arguments ["sos" "bond james bond"]}
   {:expected false
    :arguments ["abba" "dog dog dog dog"]}])

(defn transform [array]
  (let [alphabete (distinct array)]
    (map #(.indexOf alphabete %) array)))

(defn solution [pattern words]
  (=
   (transform (vec (seq pattern)))
   (transform (s/split words #" "))))
