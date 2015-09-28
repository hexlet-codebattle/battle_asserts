(ns battle-solutions.word-chains-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn word-chains
  [words]
  (letfn [(levenshtein-distance
            [seq1 seq2]
            (cond
              (empty? seq1) (count seq2)
              (empty? seq2) (count seq1)
              :else (min
                     (+ (if (= (first seq1) (first seq2)) 0 1)
                        (levenshtein-distance (rest seq1) (rest seq2)))
                     (inc (levenshtein-distance (rest seq1) seq2))
                     (inc (levenshtein-distance seq1 (rest seq2))))))
          (distances
           [word]
           (count (filter #(= 1 (levenshtein-distance word %))
                          words)))]
    (> 2 (count (filter #(>= 1 (distances %))
                        words)))))

(deftest test-asserts
  (let [input ["hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"]]
    (assert-equal true (word-chains input)))
  (let [input ["cot" "hot" "bat" "fat"]]
    (assert-equal false (word-chains input)))
  (let [input ["to" "top" "stop" "tops" "toss"]]
    (assert-equal false (word-chains input)))
  (let [input ["spout" "do" "pot" "pout" "spot" "dot"]]
    (assert-equal true (word-chains input)))
  (let [input ["share" "hares" "shares" "hare" "are"]]
    (assert-equal true (word-chains input)))
  (let [input ["share" "hares" "hare" "are"]]
    (assert-equal false (word-chains input))))
