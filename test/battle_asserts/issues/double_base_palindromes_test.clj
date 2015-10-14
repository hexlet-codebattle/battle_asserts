(ns battle-asserts.issues.double-base-palindromes-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.double-base-palindromes :as issue]))

(deftest test-asserts
  (is (= 1 (issue/solution 1)))
  (is (= 5 (issue/solution 3)))
  (is (= 99 (issue/solution 7)))
  (is (= 1758571 (issue/solution 20))))
