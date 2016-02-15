(ns battle-asserts.issues.group-multiple-by-occurence-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.group-multiple-by-occurence :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (first (apply issue/solution v))
                   (ffirst v))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
