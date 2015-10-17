(ns battle-asserts.issues.double-base-palindromes
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
                 Find n-th palindromic number.")

(defn arguments-generator []
  (gen/tuple (gen/choose 1 20)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [num]
  (letfn [(palindromic? [n]
            (= (seq (str n))
               (reverse (str n))))
          (binary-palindromic? [n]
                               (palindromic? (Integer/toBinaryString n)))]
    (->> (range 1 10000000)
         (lazy-seq)
         (filter #(and (palindromic? %)
                       (binary-palindromic? %)))
         (take num)
         (last))))
