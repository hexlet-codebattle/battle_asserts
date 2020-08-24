(ns battle-asserts.issues.sam-with-frodo-test
  (:require [clojure.test :refer :all]
            [test-helper :as h]
            [battle-asserts.issues.sam-with-frodo :as issue]))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
