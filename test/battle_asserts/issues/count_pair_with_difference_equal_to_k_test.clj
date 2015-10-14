(ns battle-asserts.issues.count-pair-with-difference-equal-to-k-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.count-pair-with-difference-equal-to-k :as issue]))

(deftest test-solution
  (is (= 2 (issue/solution [1 5 3 4 2] 3)))
  (is (= 5 (issue/solution [8 12 16 4 0 20] 4)))
  (is (= 7 (issue/solution [1 4 3 0 2 5 7 8 9 6] 3)))
  (is (= 3 (issue/solution [1 2 3 4 4 2 2 1] 0))))
