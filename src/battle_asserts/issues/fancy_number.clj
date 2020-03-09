(ns battle-asserts.issues.fancy-number
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given a number, find whether it is fancy or not.
                 A fancy number is one which when rotated 180 degrees is the same.
                 When rotated, 6 becomes 9, 9 becomes 6, and 8, 1, 0 become themselves (do not change).")

(def signature
  {:input  [{:argument-name "num_str" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(def mapping {\0 \0 \1 \1 \6 \9 \8 \8 \9 \6})

(defn arguments-generator []
  (letfn [(fancy-number []
            (let [length (+ 2 (rand-int 7))
                  half-length (quot (inc length) 2)
                  half (repeatedly half-length #(rand-nth (vals mapping)))]
              (->>
               half
               (map mapping)
               (take (quot length 2))
               reverse
               (concat half)
               s/join)))
          (almost-fancy-number []
            (let [fancy-number (vec (fancy-number))
                  length (count fancy-number)
                  half-length (quot length 2)
                  not-fancy-numerals [\2 \3 \4 \5 \7]]
              (s/join (apply conj (subvec fancy-number 0 half-length)
                             (rand-nth not-fancy-numerals)
                             (subvec fancy-number (- length half-length))))))
          (number []
            (let [length (+ 2 (rand-int 7))
                  numbers (flatten (vec mapping))]
              (s/join (repeatedly length #(rand-nth numbers)))))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 fancy-number))
                            (gen/elements (repeatedly 50 almost-fancy-number))
                            (gen/elements (repeatedly 50 number))]))))

(def test-data
  [{:expected true
    :arguments ["9081806"]}
   {:expected true
    :arguments ["916"]}
   {:expected true
    :arguments ["9088806"]}
   {:expected false
    :arguments ["121"]}
   {:expected false
    :arguments ["996"]}
   {:expected true
    :arguments ["96"]}])

(defn solution [n]
  (let [last-index (dec (count n))]
    (->>
     n
     (map-indexed #(= (mapping %2)
                      (nth n (- last-index %1))))
     (every? true?))))
