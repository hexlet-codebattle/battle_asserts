(ns battle-solutions.excel-sheet-column-title-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn convert-to-title [num]
  (if (zero? num)
    ""
    (let [n (dec num) A (int \A) size 26]
      (format "%s%s"
              (->
               n
               (quot size)
               convert-to-title)
              (->
               n
               (mod size)
               (+ A)
               char)))))

(deftest test-asserts
  (assert-equal "A" (convert-to-title 1))
  (assert-equal "Z" (convert-to-title 26))
  (assert-equal "YP" (convert-to-title 666))
  (assert-equal "CPN" (convert-to-title 2458))
  (assert-equal "AJHX" (convert-to-title 24568)))
