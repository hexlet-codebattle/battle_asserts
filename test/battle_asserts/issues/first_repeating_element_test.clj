(ns battle-asserts.issues.first-repeating-element-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.first-repeating-element :as issue]))

(deftest test-solution
  (is (= 5 (issue/solution [10 5 3 4 3 5 6])))
  (is (= 6 (issue/solution [6 10 5 4 9 120 4 6 10])))
  (is (= 7 (issue/solution [6 10 7 4 9 120 4 7])))
  (is (= 8 (issue/solution [8 8 7 4 9 120 4 7]))))
