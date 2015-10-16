(ns battle-asserts.issues.pair-with-maximum-product-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.pair-with-maximum-product :as issue]))

(deftest test-solution
  (is (= [6 7] (issue/solution [1 4 3 6 7 0])))
  (is (= [-5 -4] (issue/solution [-1 -3 -4 2 0 -5])))
  (is (= [3 4] (issue/solution [-1 -2 -4 -3 0 4 3 2 1]))))
