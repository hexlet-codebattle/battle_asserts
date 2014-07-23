(ns battle-solutions.anagram-finder-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn anagram-finder
  [words]
  (letfn [(prepare
            [word]
            (vec (sort (char-array word))))

          (anagram?
            [word candidate]
            (= 0 (compare (prepare word) (prepare candidate))))

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


(deftest test-asserts
  (let [input ["meat" "mat" "team" "mate" "eat"]
        output [["mate" "meat" "team"]]]
    (assert-equal output (anagram-finder input)))
  (let [input ["veer" "lake" "item" "kale" "mite" "ever"]
        output [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]]
    (assert-equal output (anagram-finder input))))
