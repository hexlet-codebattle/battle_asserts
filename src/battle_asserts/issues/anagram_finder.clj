(ns battle-asserts.issues.anagram-finder
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def tags ["strings" "collections"])

(def description
  {:en "Find all the anagrams in a vector of words. Your function should return a vector of vectors, where each sub-vector is a group of words which are anagrams of each other. Words without any anagrams should not be included in the result. If there is no anagram, return subvector with string \"anagrams not found!\""
   :ru "Найдите все анаграммы в векторе слов. Функция должна возвращать вектор векторов, где каждый подвектор - это набор слов которые являеются анаграммой друг к другу. Слова без анагармм не должны попадать в результат. Если анаграмм во входящем векторе нет - возвращается подвектор со строкой \"anagrams not found!\""})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "string"}}}}})

(defn arguments-generator []
  (let [words1 (faker/words {:lang :en :n 20})
        words2 (map #(s/join (shuffle (seq %)))
                    words1)
        words3 (map #(s/join (shuffle (seq %)))
                    words1)
        words (vec (concat words1 words2 words3))]
    (gen/tuple (gen/vector (gen/elements words) 10 20))))

(def test-data
  [{:arguments [["veer" "lake" "item" "kale" "mite" "ever" "rev"]]
    :expected  [["veer" "ever"] ["lake" "kale"] ["item" "mite"]]}
   {:arguments [["meat" "mat" "team" "mate" "eat" "mate"]]
    :expected  [["meat" "team" "mate" "mate"]]}
   {:arguments [["there" "is" "no" "anagrams" "foo" "bar"]]
    :expected  [["anagrams not found!"]]}
   {:arguments [["guohc" "guohc" "cough" "morning" "adigrne" "osls" "sneeze" "knowledge" "nitwer" "distribution" "water" "ewvi" "event" "oriintdusbti" "trnwie" "water" "nuaegalg" "osls" "gelugaan" "question"]]
    :expected [["guohc" "guohc" "cough"] ["osls" "osls"] ["nitwer" "trnwie"] ["distribution" "oriintdusbti"] ["water" "water"] ["nuaegalg" "gelugaan"]]}])

(defn prepare-anagram [word]
  (s/join (sort word)))

(defn solution [words]
  (let [uniq (mapv prepare-anagram (distinct words))
        result (filterv #(not= (count %) 1)
                        (distinct (mapv (fn [word]
                                          (filterv #(= (prepare-anagram %) word) words)) uniq)))]
    (if (empty? (first result))
      [["anagrams not found!"]]
      result)))
