(ns battle-asserts.issues.more-than-medium-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.more-than-medium :as issue]))

(deftest test-solution
  (is (= ["This" "sample" "string"] (issue/solution "This is a sample string")))
  (is (= ["another" "sample"] (issue/solution "Some another sample")))
  (is (= [] (issue/solution "Do, do, do, do... do it!"))))
