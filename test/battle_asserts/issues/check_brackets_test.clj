(ns battle-asserts.issues.check-brackets-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.check-brackets :as issue]))

(deftest test-solution
  (is (issue/solution " "))
  (is (issue/solution "()[]{}<>"))
  (is (issue/solution "<(){[]}>"))
  (is (not (issue/solution "())")))
  (is (not (issue/solution "()(")))
  (is (not (issue/solution "({)}")))
  (is (not (issue/solution "{)][(}"))))
