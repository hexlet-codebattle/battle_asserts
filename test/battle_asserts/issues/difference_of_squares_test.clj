(ns battle-asserts.issues.difference-of-squares-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.difference-of-squares :as issue]))

(deftest test-asserts
  (is (= 170 (issue/solution 5)))
  (is (= 25164150 (issue/solution 100)))
  (is (= 100100 (issue/solution 25)))
  (is (= 0 (issue/solution 1))))
