(ns battle-asserts.issues.power-digits-sum-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.power-digits-sum :as issue]))

(deftest test-solution
  (is (= 13 (issue/solution 8)))
  (is (= 26 (issue/solution 15))))
