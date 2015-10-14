(ns battle-asserts.issues.counting-array-elements-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.counting-array-elements :as issue]))

(deftest test-solution
  (let [arr [:cat, :dog, :fish, :fish]]
    (is (= {:cat 1, :dog 1, :fish 2} (issue/solution arr))))
  (let [arr [:Spam, :egg, :Spam, :Spam, :bacon, :Spam]]
    (is (= {:bacon 1, :egg 1, :Spam 4} (issue/solution arr)))))
