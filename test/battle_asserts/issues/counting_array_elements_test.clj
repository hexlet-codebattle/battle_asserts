(ns battle-asserts.issues.counting-array-elements-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.counting-array-elements :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (set (keys (apply issue/solution v)))
                   (set (first v)))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
