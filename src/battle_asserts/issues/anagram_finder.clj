(ns battle-asserts.issues.anagram-finder
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description
  "Write a function which finds all the anagrams in a vector of words.
  Your function should return sorted vector of vectors, where each sub-vec is a group of words which are anagrams of each other.
  Words without any anagrams should not be included in the result.")

(defn arguments-generator
  []
  (let [words1 (faker/words {:lang :en :n 8})
        words2 (map #(s/join (shuffle (seq %)))
                    words1)
        words3 (map #(s/join (shuffle (seq %)))
                    words1)
        words (vec (concat words1 words2 words3))]
    (gen/tuple (apply gen/tuple (repeatedly 6 #(gen/elements words))))))

; (gen/sample (arguments-generator) 5)

(def test-data
  [{:arguments [["meat" "mat" "team" "mate" "eat"]]
    :expected [["mate" "meat" "team"]]}
   {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
    :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution
  [words]
  (letfn [(prepare
            [word]
            (vec (sort (char-array word))))

          (anagram?
           [word candidate]
           (zero? (compare (prepare word) (prepare candidate))))

          (anagrams-for
           [word anagrams]
           (let [confirmed (filter #(anagram? word %) anagrams)
                 unconfirmed (filter #(not (anagram? word %)) anagrams)]
             (if (empty? confirmed)
               []
               (cons (vec (sort confirmed))
                     (anagrams-for (first unconfirmed) unconfirmed)))))]

    (vec (sort (filter #(< 1 (count %))
                       (anagrams-for (first words) words))))))
