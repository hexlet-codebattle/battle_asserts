(ns battle-asserts.issues.concat-chess-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.concat-chess :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (count (apply issue/solution v))
                   (apply + (map count v)))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
