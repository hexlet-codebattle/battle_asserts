(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [clojure.string :as s]
            [battle-asserts.issues.arrange-numbers-to-form-biggest-number :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (>= (BigInteger. (apply issue/solution v))
                    (BigInteger. (s/join (first v))))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
