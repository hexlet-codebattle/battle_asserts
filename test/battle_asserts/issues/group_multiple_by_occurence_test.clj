(ns battle-asserts.issues.group-multiple-by-occurence-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.group-multiple-by-occurence :as issue]))

(deftest test-solution
  (is (= [5 5 3 3 3 1] (issue/solution [5 3 5 1 3 3])))
  (is (= [4 4 4 6 6 9 9 2 3 10] (issue/solution [4 6 9 2 3 4 9 6 10 4])))
  (is (= [10 10 10 5 3 3 4 1] (issue/solution [10 5 3 10 10 4 1 3]))))
