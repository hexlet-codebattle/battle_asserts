(ns battle-asserts.issues.anagram-finder-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.anagram-finder :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (let [result (apply issue/solution v)]
                      (= (count (filter vector? result)) (count result)))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
