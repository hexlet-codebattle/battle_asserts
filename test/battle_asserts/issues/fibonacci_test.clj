(ns battle-asserts.issues.fibonacci-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.fibonacci :as issue]))

(deftest test-solution
  (is (= 0 (issue/solution 0)))
  (is (= 1 (issue/solution 1)))
  (is (= 3 (issue/solution 4)))
  (is (= 13 (issue/solution 7)))
  (is (= 55 (issue/solution 10))))

