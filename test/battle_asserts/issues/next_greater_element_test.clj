(ns battle-asserts.issues.next-greater-element-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.next-greater-element :as issue]))

(deftest test-solution
  (is (= [5 25 25 -1] (issue/solution [4 5 2 25])))
  (is (= [-1 12 12 -1] (issue/solution [13 7 6 12])))
  (is (= [6 8 12 5 5 12 -1 9 -1] (issue/solution [3 6 8 2 1 5 12 4 9])))
  (is (= [4 2 4 -1] (issue/solution [3 1 2 4]))))
