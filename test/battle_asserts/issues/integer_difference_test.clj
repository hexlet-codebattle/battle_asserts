(ns battle-asserts.issues.integer-difference-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.integer-difference :as issue]))

(deftest test-solution
  (is (= 3 (issue/solution 4 [1 1 5 6 9 16 27])))
  (is (= 4 (issue/solution 2 [1 1 3 3]))))
