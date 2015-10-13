(ns battle-asserts.issues.check-parentheses-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.check-parentheses :as issue]))

(deftest test-asserts
  (is (issue/solution ""))
  (is (issue/solution " "))
  (is (issue/solution "()"))
  (is (issue/solution " ( )(  )"))
  (is (issue/solution "(() )"))
  (is (not (issue/solution ") ")))
  (is (not (issue/solution "(")))
  (is (not (issue/solution ") (")))
  (is (not (issue/solution "( )  )")))
  (is (not (issue/solution "(( )"))))
