(ns battle-asserts.issues.excel-sheet-column-title-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.excel-sheet-column-title :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (instance? String (apply issue/solution v))))

(h/generate-tests issue/test-data issue/solution)
