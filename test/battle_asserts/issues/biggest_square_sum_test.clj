(ns battle-asserts.issues.biggest-square-sum-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.biggest-square-sum :as issue]))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
