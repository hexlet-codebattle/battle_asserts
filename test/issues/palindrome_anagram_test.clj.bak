(ns battle-solutions.palindrome-anagram-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-palindrome-anagram [s]
  (->> s
       (frequencies)
       (vals)
       (reduce #(if (odd? %2)
                  (if (= %1 0)
                    (inc %1)
                    (reduced (inc %1)))
                  %1)
               0)
       (>= 1)))

(deftest test-asserts
  (assert-true (is-palindrome-anagram ""))
  (assert-true (is-palindrome-anagram "a"))
  (assert-true (is-palindrome-anagram "aa"))
  (assert-true (is-palindrome-anagram "aab"))
  (assert-true (is-palindrome-anagram "aabb"))
  (assert-true (is-palindrome-anagram "aabbc"))
  (assert-true (is-palindrome-anagram "abcabc"))
  (assert-true (not (is-palindrome-anagram "ab")))
  (assert-true (not (is-palindrome-anagram "aabbcd")))
  (assert-true (not (is-palindrome-anagram "aaabbb"))))
