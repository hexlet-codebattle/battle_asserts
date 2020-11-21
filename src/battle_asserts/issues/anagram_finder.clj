(ns battle-asserts.issues.anagram-finder
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description
  "Find all the anagrams in a vector of words.
  Your function should return a vector of vectors, where each sub-vector is a group of words which are anagrams of each other.
  Words without any anagrams should not be included in the result. If there is no anagram, return subvector with string \"anagrams not found!\"")

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
    :expected  [["anagrams not found!"]]}])

(defn solution [words]
  (let [result (->> words
                    (group-by count)
                    vals
                    (map #(apply group-by set %&))
                    (mapcat vals)
                    (filterv next))]
    (if (empty? (first result))
      [["anagrams not found!"]]
      result)))
