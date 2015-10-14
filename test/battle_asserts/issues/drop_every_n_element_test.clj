(ns battle-asserts.issues.drop-every-n-element-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.drop-every-n-element :as issue]))

(deftest test-asserts
  (is (= '("a" "b" "d" "e" "g" "h" "k")) (issue/solution 3 '("a" "b" "c" "d" "e" "f" "g" "h" "i" "k")))
  (is (= [0 2 4 6 8] (issue/solution 2 [0 1 2 3 4 5 6 7 8 9])))
  (is (= [0 1 2 3] (issue/solution 5 [0 1 2 3])))
  (is (= [] (issue/solution 1 [0 1 2 3 4 5 6 7]))))
