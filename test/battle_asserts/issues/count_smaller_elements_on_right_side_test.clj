(ns battle-asserts.issues.count-smaller-elements-on-right-side-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.count-smaller-elements-on-right-side :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)
                      last-element (last result)]
                  (or (= last-element 0)
                      (= last-element nil)))))

(h/generate-tests issue/test-data issue/solution)
