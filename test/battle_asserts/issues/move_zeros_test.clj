(ns battle-asserts.issues.move-zeros-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.move-zeros :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)]
                  (if (some zero? result)
                    (zero? (last result))
                    true))))

(h/generate-tests issue/test-data issue/solution)
