(ns battle-asserts.issues.first-repeating-element-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.first-repeating-element :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)]
                  (if (nil? result)
                    true
                    (instance? Long result)))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
