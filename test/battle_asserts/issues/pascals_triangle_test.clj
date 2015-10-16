(ns battle-asserts.issues.pascals-triangle-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.pascals-triangle :as issue]))

(deftest test-solution
  (is (= [1] (issue/solution 0)))
  (is (= [1 1] (issue/solution 1)))
  (is (= [1 2 1] (issue/solution 2)))
  (is (= [1 3 3 1] (issue/solution 3)))
  (is (= [1 4 6 4 1] (issue/solution 4))))
