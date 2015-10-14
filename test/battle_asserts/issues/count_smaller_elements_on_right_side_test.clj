(ns battle-asserts.issues.count-smaller-elements-on-right-side-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.count-smaller-elements-on-right-side :as issue]))

(deftest test-solution
  (is (= [6 1 1 1 0 1 0] (issue/solution [12 1 2 3 0 11 4])))
  (is (= [4 3 2 1 0] (issue/solution [5 4 3 2 1])))
  (is (= [0 0 0 0 0] (issue/solution [1 2 3 4 5])))
  (is (= [6 5 7 4 1 3 0 2 0 0] (issue/solution [7 6 10 5 2 8 1 9 3 4]))))
