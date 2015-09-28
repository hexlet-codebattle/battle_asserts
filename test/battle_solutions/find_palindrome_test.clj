(ns battle-solutions.find-palindrome-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn find-palindrome [st]
  (->> st
       (iterate #(subs % 1))
       (take-while seq)
       (map #(rest (reductions str (str) %)))
       (flatten)
       (filter #(and (< 1 (count %)) (= % (clojure.string/reverse %))))
       (sort-by count)
       (reverse)
       (first)))

(deftest test-asserts
  (assert-equal "yzzy" (find-palindrome "xyzzy"))
  (let [pal "dhfdkjfffhhfffjkdfhd"
        st "afbbbfjdjklgdfdhfdkjfffhhfffjkdfhdhkyejejfjkd"]
    (assert-equal pal (find-palindrome st)))
  (let [pal "racecar"
        st "bartarcarracecarbartar"]
    (assert-equal pal (find-palindrome st)))
  (let [pal "46264"
        st "3.1415926535897932384626433832795028841971"]
    (assert-equal pal (find-palindrome st))))
