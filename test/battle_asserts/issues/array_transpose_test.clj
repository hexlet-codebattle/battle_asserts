(ns battle-asserts.issues.array-transpose-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.array-transpose :as issue]))

(deftest test-asserts
  (is [[1 :a] [2 :b] [3 :c]] (issue/solution [[1 2 3] [:a :b :c]]))
  (is [[1 3 5] [2 4 6]] (issue/solution [[1 2] [3 4] [5 6]]))
  (is [] (issue/solution [])))
