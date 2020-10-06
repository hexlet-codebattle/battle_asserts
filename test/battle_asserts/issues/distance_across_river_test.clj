(ns battle-asserts.issues.distance-across-river-test
  (:require [battle-asserts.issues.distance-across-river :as issue]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :as ct]
            [clojure.test.check.properties :as prop]
            [test-helper :as h]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (number? (apply issue/solution v))))

(ct/defspec spec-signature
  20
  (prop/for-all [v (issue/arguments-generator)]
                (true? (h/generate-signatures issue/signature v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))

(deftest test-data-tests
  (h/generate-data-tests issue/test-data issue/signature))