(ns battle-asserts.issues.leaders-in-an-array-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.leaders-in-an-array :as issue]))

(deftest test-solution
  (is (= [17 5 2] (issue/solution [16 17 4 3 5 2])))
  (is (= [67 45 35 8] (issue/solution [4 3 7 12 6 67 5 45 34 35 2 8])))
  (is (= [12 8 7 6] (issue/solution [12 10 12 8 7 6])))
  (is (= [5 4] (issue/solution [1 2 3 4 5 4]))))
