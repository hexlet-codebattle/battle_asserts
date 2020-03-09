(ns battle-asserts.issues.find-palindrome
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "A palindrome is a string that is written the same forward as it is in reverse.
                 Write a method to return the longest palindrome in a given string.")

(def signature
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(alphabet []
            (map char (range (int \a) (inc (int \z)))))
          (palindrome [length]
            (let [half-length (quot (inc length) 2)
                  half (repeatedly length #(rand-nth (alphabet)))
                  palindrome (concat half (subvec (vec (reverse half)) (rem length 2)))]
              (s/join palindrome)))
          (string-with-palindrome []
            (let [palindrome-count (inc (rand-int 3))
                  max-palindrome-length (+ 4 (rand-int 7))
                  palindroms (map #(palindrome (- max-palindrome-length %)) (range 0 palindrome-count))
                  additional-symbold (repeatedly (+ (rand-int 10) 5) #(rand-nth (alphabet)))]
              (s/join (shuffle (concat palindroms additional-symbold)))))]
    (gen/tuple (gen/elements (repeatedly 20 string-with-palindrome)))))

(def test-data
  [{:expected "yzzy"
    :arguments ["xyzzy"]}
   {:expected "dhfdkjfffhhfffjkdfhd"
    :arguments ["afbbbfjdjklgdfdhfdkjfffhhfffjkdfhdhkyejejfjkd"]}
   {:expected "racecar"
    :arguments ["bartarcarracecarbartar"]}
   {:expected "46264"
    :arguments ["3.1415926535897932384626433832795028841971"]}])

(defn solution [st]
  (->> st
       (iterate #(subs % 1))
       (take-while seq)
       (map #(rest (reductions str (str) %)))
       (flatten)
       (filter #(and (< 1 (count %)) (= % (s/reverse %))))
       (sort-by count)
       (reverse)
       (first)))
