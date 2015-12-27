(ns battle-asserts.issues.two-trains-meet-test
  (:require [battle-asserts.issues.two-trains-meet :as issue]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :as ct]
            [clojure.test.check.properties :as prop]
            [test-helper :as h]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (number? (apply issue/solution v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
