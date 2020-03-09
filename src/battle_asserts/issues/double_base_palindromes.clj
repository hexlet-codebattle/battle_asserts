(ns battle-asserts.issues.double-base-palindromes
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "The decimal number 585 is 1001001001 in binary. It is palindromic in both bases.
                 Find n-th palindromic number.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 20)))

(def test-data
  [{:expected 5
    :arguments [3]}
   {:expected 1
    :arguments [1]}
   {:expected 99
    :arguments [7]}
   {:expected 1758571
    :arguments [20]}])

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
