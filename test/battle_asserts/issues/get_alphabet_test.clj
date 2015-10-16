(ns battle-asserts.issues.get-alphabet-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.get-alphabet :as issue]))

(deftest test-solution
  (let [input "asfsfdss"
        output "adfs"]
    (is (= output (issue/solution input))))
  (let [input "acgtgcgagtg"
        output "acgt"]
    (is (= output (issue/solution input))))
  (let [input "4123214"
        output "1234"]
    (is (= output (issue/solution input))))
  (let [input "+++[><<]<-."
        output "+-.<>[]"]
    (is (= output (issue/solution input)))))
