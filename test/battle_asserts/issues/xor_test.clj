(ns battle-asserts.issues.xor-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.xor :as issue]))

(deftest test-solution
  (let [str1 "string1"
        str2 "string2"]
    (= str2 (issue/solution (issue/solution str1 str2) str1))
    (= "5*" (issue/solution "xor" "ME"))))
