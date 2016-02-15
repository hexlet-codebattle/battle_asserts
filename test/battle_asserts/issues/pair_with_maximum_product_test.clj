(ns battle-asserts.issues.pair-with-maximum-product-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.pair-with-maximum-product :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (count (apply issue/solution v))
                   2)))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
