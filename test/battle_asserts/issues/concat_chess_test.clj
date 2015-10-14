(ns battle-asserts.issues.concat-chess-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.concat-chess :as issue]))

(deftest test-solution
  (let [str1 "abcd"
        str2 "wxyz"
        output "awbxcydz"]
    (is (= output (issue/solution str1 str2))))
  (let [str1 "abcd"
        str2 "xyz"
        output "axbyczd"]
    (is (= output (issue/solution str1 str2))))
  (let [str1 "word"
        str2 "doubleword"
        output "wdoorudbleword"]
    (is (= output (issue/solution str1 str2))))
  (let [str1 "aceg"
        str2 "bdfh"
        output "abcdefgh"]
    (is (= output (issue/solution str1 str2)))))
