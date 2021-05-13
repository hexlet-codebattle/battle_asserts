(ns battle-asserts.issues.more-than-medium
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "Given a sentence (as string),
        return an array of words which are longer than the average length of all the words.
        Words a separated by a whitespace. If there is a trailing period (dot), it should be omittied. If there is no result, return \"there is no result!\""
   :ru "Дано предложение (строкой),
        верните массив слов, длина которых превышает среднюю длину всех слов.
        Слова разделены пробелом. Если присутствует точка, её следует пропустить. Если результата нет, верните \"there is no result!\"."})

(def signature
  {:input  [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [sentences (repeatedly 50 #(faker/sentence {:words-range [2 6]}))]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected ["there is no result!"]
    :arguments ["test"]}
   {:expected ["This" "sample" "string"]
    :arguments ["This is a sample string"]}
   {:expected ["another" "sample"]
    :arguments ["Some another sample"]}])

(defn solution [string]
  (let [words (re-seq #"\w+" string)
        average (/ (reduce + (map count
                                  words))
                   (count words))
        result (filterv #(> (count %) average)
                        words)]
    (if (empty? result)
      ["there is no result!"]
      result)))
