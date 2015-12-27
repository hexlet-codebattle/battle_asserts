(ns battle-asserts.issues.rect-area-test
  (:require [battle-asserts.issues.rect-area :as issue]
            [clojure.test :refer :all]
            [clojure.test.check.clojure-test :as ct]
            [clojure.test.check.properties :as prop]
            [test-helper :as h]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (apply issue/solution v)
                   (apply issue/solution (reverse v)))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
