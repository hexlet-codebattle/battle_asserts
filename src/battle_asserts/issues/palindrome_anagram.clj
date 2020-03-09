(ns battle-asserts.issues.palindrome-anagram
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Check if any anagram of a given string is a palindrome.")

(def signature
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(alphabet []
            (map char (range (int \a) (inc (int \z)))))
          (shuffled-palindrome []
            (let [length (+ 2 (rand-int 6))
                  first-half (repeatedly (quot (inc length) 2) #(rand-nth (alphabet)))
                  second-half (subvec (vec (reverse first-half)) (rem length 2))]
              (s/join (shuffle (concat first-half second-half)))))
          (almost-palindrome []
            (let [palindrome (seq (shuffled-palindrome))]
              (->>
               (frequencies palindrome)
               (filter #(even? (val %)))
               keys
               (take 2)
               (concat palindrome)
               shuffle
               s/join)))
          (string []
            (let [length (rand-int 8)]
              (s/join (repeatedly length #(rand-nth (alphabet))))))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 shuffled-palindrome))
                            (gen/elements (repeatedly 50 almost-palindrome))
                            (gen/elements (repeatedly 50 string))]))))

(def test-data
  [{:expected true
    :arguments ["abcabc"]}
   {:expected true
    :arguments [""]}
   {:expected true
    :arguments ["a"]}
   {:expected true
    :arguments ["aa"]}
   {:expected true
    :arguments ["aab"]}
   {:expected true
    :arguments ["aabb"]}
   {:expected true
    :arguments ["aabbc"]}
   {:expected false
    :arguments ["ab"]}
   {:expected false
    :arguments ["aabbcd"]}
   {:expected false
    :arguments ["aaabbb"]}])

(defn solution [s]
  (->> s
       (frequencies)
       vals
       (filter odd?)
       count
       (>= 1)))
