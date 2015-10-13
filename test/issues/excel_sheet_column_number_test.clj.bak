(ns battle-solutions.excel-sheet-column-number-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn pow [base exp]
  (apply * (repeat exp base)))

(defn title-to-number [s]
  (->>
   s
   clojure.string/reverse
   char-array
   (map-indexed #(* (- (int %2) 64) (pow 26 %1)))
   (apply +)))

(deftest test-asserts
  (assert-equal 1 (title-to-number "A"))
  (assert-equal 26 (title-to-number "Z"))
  (assert-equal 666 (title-to-number "YP"))
  (assert-equal 2458 (title-to-number "CPN"))
  (assert-equal 24568 (title-to-number "AJHX")))
