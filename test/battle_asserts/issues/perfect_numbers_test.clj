(ns battle-asserts.issues.perfect-numbers-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.perfect-numbers :as issue]))

(deftest test-solution
  (is (issue/solution 6))
  (is (not (issue/solution 7)))
  (is (issue/solution 496))
  (is (not (issue/solution 500)))
  (is (issue/solution 8128)))
