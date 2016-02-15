(ns battle-asserts.issues.count-pair-with-difference-equal-to-k-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.count-pair-with-difference-equal-to-k :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (>= (apply issue/solution v)
                    0)))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
