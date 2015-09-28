(ns battle-solutions.levenshtein-distance-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn levenshtein-distance
  [seq1 seq2]
  (cond
    (empty? seq1) (count seq2)
    (empty? seq2) (count seq1)
    :else (min
           (+ (if (= (first seq1) (first seq2)) 0 1)
              (#'levenshtein-distance (rest seq1) (rest seq2)))
           (inc (#'levenshtein-distance (rest seq1) seq2))
           (inc (#'levenshtein-distance seq1 (rest seq2))))))

(deftest test-asserts
  (assert-equal 3 (levenshtein-distance "kitten" "sitting"))
  (assert-equal 1 (levenshtein-distance "clojure" "closure"))
  (assert-equal 2 (levenshtein-distance "xyx" "xyyyx"))
  (assert-equal 6 (levenshtein-distance "" "123456"))
  (assert-equal 0 (levenshtein-distance [] []))
  (assert-equal 2 (levenshtein-distance [1 2 3 4] [0 2 3 4 5])))
