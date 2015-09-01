(ns battle-solutions.palindrome-anagram-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-palindrome-anagram? [s]
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
  (assert (is-palindrome-anagram? ""))
  (assert (is-palindrome-anagram? "a"))
  (assert (is-palindrome-anagram? "aa"))
  (assert (is-palindrome-anagram? "aab"))
  (assert (is-palindrome-anagram? "aabb"))
  (assert (is-palindrome-anagram? "aabbc"))
  (assert (is-palindrome-anagram? "abcabc"))
  (assert (not (is-palindrome-anagram? "ab")))
  (assert (not (is-palindrome-anagram? "aabbcd")))
  (assert (not (is-palindrome-anagram? "aaabbb"))))
