(ns battle-asserts.issues.excel-sheet-column-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.excel-sheet-column-number :as issue]))

(deftest test-solution
  (is (= 1 (issue/solution "A")))
  (is (= 26 (issue/solution "Z")))
  (is (= 666 (issue/solution "YP")))
  (is (= 2458 (issue/solution "CPN")))
  (is (= 24568 (issue/solution "AJHX"))))
