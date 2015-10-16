(ns battle-asserts.issues.happy-numbers-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.happy-numbers :as issue]))

(deftest test-solution
  (is (issue/solution 7))
  (is (issue/solution 986543210))
  (is (not (issue/solution 2)))
  (is (not (issue/solution 189))))
