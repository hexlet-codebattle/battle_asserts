(ns battle-asserts.issues.anagram-finder-test
  (:require [clojure.test :refer :all]
            [battle-asserts.issues.anagram-finder :as issue] ))

(deftest test-solution
  (let [input ["meat" "mat" "team" "mate" "eat"]
        output [["mate" "meat" "team"]]]
    (is (= output (issue/solution input))))
  (let [input ["veer" "lake" "item" "kale" "mite" "ever"]
        output [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]]
    (is (= output (issue/solution input)))))

