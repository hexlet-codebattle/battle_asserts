(ns battle-asserts.issues.build-hash-with-default-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.build-hash-with-default :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [default-value (second v)]
                  (every? #(= % default-value)
                          (vals (apply issue/solution v))))))

(h/generate-tests issue/test-data issue/solution)
