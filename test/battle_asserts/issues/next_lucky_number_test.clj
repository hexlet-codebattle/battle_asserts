(ns battle-asserts.issues.next-lucky-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.next-lucky-number :as issue]))

(deftest test-solution
  (is (= 5 (issue/solution 4)))
  (is (= 53 (issue/solution 48)))
  (is (= 333 (issue/solution 130)))
  (is (= 333 (issue/solution 98)))
  (is (= 3333 (issue/solution 949)))
  (is (= 353 (issue/solution 339))))
