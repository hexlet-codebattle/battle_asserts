(ns battle-asserts.issues.factorial-trailing-zeroes-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.factorial-trailing-zeroes :as issue]))

(deftest test-solution
  (is (= 0 (issue/solution 0)))
  (is (= 1 (issue/solution 5)))
  (is (= 1 (issue/solution 7)))
  (is (= 4 (issue/solution 23)))
  (is (= 6 (issue/solution 28)))
  (is (= 22 (issue/solution 99))))
