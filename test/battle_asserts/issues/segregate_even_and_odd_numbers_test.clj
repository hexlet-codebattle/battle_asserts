(ns battle-asserts.issues.segregate-even-and-odd-numbers-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.segregate-even-and-odd-numbers :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)]
                  (<= (count (partition-by even? result))
                      2))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
