(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.arrange-numbers-to-form-biggest-number :as issue]))

(deftest test-solution
  (is (= 654321 (issue/solution [1 2 3 4 5 6])))
  (is (= 6054854654 (issue/solution [54 546 548 60])))
  (is (= 998764543431 (issue/solution [1 34 3 98 9 76 45 4])))
  (is (= 9908988444332412 (issue/solution [43 44 12 324 90 9 88 89]))))
