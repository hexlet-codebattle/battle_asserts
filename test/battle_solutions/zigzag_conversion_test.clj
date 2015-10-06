(ns battle-solutions.zigzag-conversion-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn zigzag
  [string height]
  (clojure.string/join
    (flatten (for [i (range height)]
               (map #(nth string %)
                    (take-while
                      #(< % (count string))
                      (if (= i (quot height 2))
                        (iterate #(+ (inc (quot height 2)) %) i)
                        (iterate #(+ (inc height) %) i))))))))

(deftest test-asserts
  (let [str1 "PAYPALISHIRING"
        str2 "PAHNAPLSIIGYIR" ]
    (assert-equal str2 (zigzag str1 3)))
  (let [str1 "Pneumonoultramicroscopicsilicovolcanoconiosis"
        str2 "Pnassvosnomciocieouriooclolnoosulcpicnmtricai"]
    (assert-equal str2 (zigzag str1 5))))
