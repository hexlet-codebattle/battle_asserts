(ns battle-asserts.issues.distance-across-river-test
  (:require [battle-asserts.issues.distance-across-river :as issue]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :as ct]
            [clojure.test.check.properties :as prop]
            [test-helper :as h]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [r (apply issue/solution v)]
                  (= (double (int r)) r))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
