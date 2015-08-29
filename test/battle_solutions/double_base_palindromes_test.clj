(ns battle-solutions.double-base-palindromes-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn double-base-palindromes
  [num]
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

(deftest test-asserts
  (assert-equal 1 (double-base-palindromes 1))
  (assert-equal 5 (double-base-palindromes 3))
  (assert-equal 99 (double-base-palindromes 7))
  (assert-equal 1758571 (double-base-palindromes 20)))
