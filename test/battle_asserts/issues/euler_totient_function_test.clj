(ns battle-asserts.issues.euler-totient-function-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.euler-totient-function :as issue]))

(deftest test-solution
  (is (= 1 (issue/solution 1)))
  (is (= 4 (issue/solution 10)))
  (is (= 16 (issue/solution 40)))
  (is (= 60 (issue/solution 99))))
