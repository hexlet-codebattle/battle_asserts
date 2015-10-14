(ns battle-asserts.issues.domino-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.domino :as issue]))

(deftest test-asserts
  (is (= 0  (issue/solution 0)))
  (is (= 3  (issue/solution 1)))
  (is (= 12 (issue/solution 2)))
  (is (= 30 (issue/solution 3))))
