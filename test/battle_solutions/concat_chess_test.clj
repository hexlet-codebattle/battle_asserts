(ns battle-solutions.concat-chess-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn concat-chess
  [str1 str2]
  (let [s1 (seq str1)
        s2 (seq str2)
        maxcount (apply max (map count [s1 s2]))]
    (-> (map list
             (take maxcount (concat s1 (repeat nil)))
             (take maxcount (concat s2 (repeat nil))))
        flatten
        clojure.string/join)))

(deftest test-asserts
  (let [str1 "abcd"
        str2 "wxyz"
        output "awbxcydz"]
    (assert-equal output (concat-chess str1 str2)))
  (let [str1 "abcd"
        str2 "xyz"
        output "axbyczd"]
    (assert-equal output (concat-chess str1 str2)))
  (let [str1 "word"
        str2 "doubleword"
        output "wdoorudbleword"]
    (assert-equal output (concat-chess str1 str2)))
  (let [str1 "aceg"
        str2 "bdfh"
        output "abcdefgh"]
    (assert-equal output (concat-chess str1 str2))))
