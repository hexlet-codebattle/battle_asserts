(ns battle-asserts.issues.excel-sheet-column-title-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.excel-sheet-column-title :as issue]))

(deftest test-solution
  (is (= "A" (issue/solution 1)))
  (is (= "Z" (issue/solution 26)))
  (is (= "YP" (issue/solution 666)))
  (is (= "CPN" (issue/solution 2458)))
  (is (= "AJHX" (issue/solution 24568))))
