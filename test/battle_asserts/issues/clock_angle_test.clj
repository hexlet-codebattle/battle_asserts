(ns battle-asserts.issues.clock-angle-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.clock-angle :as issue]))

(deftest test-asserts
  (is 0.0 (issue/solution 0 0))
  (is 7.5 (issue/solution 3 15))
  (is 82.5 (issue/solution 0 15))
  (is 275.0 (issue/solution 0 50))
  (is 157.5 (issue/solution 3 45)))
