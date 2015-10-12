(ns battle-asserts.issues.chess-row-color-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.chess-row-color :as issue]))

(deftest test-asserts
  (is (issue/solution "A" 1))
  (is (issue/solution "H" 8))
  (is (issue/solution "D" 4))
  (is (issue/solution "G" 7))
  (is (false? (issue/solution "A" 8)))
  (is (false? (issue/solution "H" 1)))
  (is (false? (issue/solution "E" 8)))
  (is (false? (issue/solution "E" 4))))
