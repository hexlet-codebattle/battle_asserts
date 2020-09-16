(ns battle-asserts.issues.counting-array-elements-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.counting-array-elements :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (set (keys (apply issue/solution v)))
                   (set (first v)))))

(ct/defspec spec-signature
  20
  (prop/for-all [v (issue/arguments-generator)]
                (true? (h/generate-signatures issue/signature v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
