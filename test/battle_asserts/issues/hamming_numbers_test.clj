(ns battle-asserts.issues.hamming-numbers-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.hamming-numbers :as issue]))

(deftest test-solution
  (is (= 5 (issue/solution 5)))
  (is (= 36 (issue/solution 20)))
  (is (= 937500 (issue/solution 500)))
  (is (= 51200000 (issue/solution 1000))))
