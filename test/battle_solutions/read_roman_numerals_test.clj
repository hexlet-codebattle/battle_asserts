(ns battle-solutions.read-roman-numerals-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn read-roman-numerals
  [roman]
  (let [digits {"M" 1000, "CM" 900, "D" 500, "CD" 400, "C" 100, "XC" 90, "L" 50, "XL" 40, "X" 10, "IX" 9, "V" 5, "IV" 4, "I" 1}]
    (loop [[char1 char2 & rest-chars] roman
           answer 0]
      (if-let [num (and char2
                        (get digits (str char1 char2)))]
        (recur rest-chars (+ answer num))
        (if-let [num (get digits (str char1))]
          (recur (apply str char2 rest-chars) (+ answer num))
          answer)))))

(deftest test-asserts
  (assert-equal 14 (read-roman-numerals "XIV"))
  (assert-equal 827 (read-roman-numerals "DCCCXXVII"))
  (assert-equal 3999 (read-roman-numerals "MMMCMXCIX"))
  (assert-equal 48 (read-roman-numerals "XLVIII")))
