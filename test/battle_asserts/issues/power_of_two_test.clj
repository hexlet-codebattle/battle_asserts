(ns battle-asserts.issues.power-of-two-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.power-of-two :as issue]))

(deftest test-solution
  (is (issue/solution 16))
  (is (not (issue/solution 20)))
  (is (issue/solution 1))
  (is (not (issue/solution 258)))
  (is (issue/solution 512))
  (is (not (issue/solution 513)))
  (is (issue/solution 1024))
  (is (not (issue/solution 0))))
