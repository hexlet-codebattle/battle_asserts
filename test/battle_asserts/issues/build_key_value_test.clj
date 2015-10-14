(ns battle-asserts.issues.build-key-value-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.build-key-value :as issue]))

(deftest test-solution
  (let [easy {"a[b]" 3}]
    (is (= easy (issue/solution {"a" {"b" 3}}))))
  (let [medium {"x[0]" "1" "x[1]" "2" "x[2]" "3"}]
    (is (= medium (issue/solution {"x" ["1" "2" "3"]}))))
  (let [hard {"a[d][0]" 1 "a[d][1]" 2}]
    (is (= hard (issue/solution {"a" {"d" [1 2]}}))))
  (let [nightmare {"a[b]" 3 "a[c]" 2 "a[d][0]" 1 "a[d][1]" 2 "x[0]" "1" "x[1]" "2" "x[2]" "3"}]
    (is (= nightmare (issue/solution {"a" {"b" 3 "c" 2 "d" [1 2]} "x" ["1" "2" "3"]})))))
