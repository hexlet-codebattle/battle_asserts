(ns battle-asserts.issues.next-lucky-number-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [clojure.string :as s]
            [battle-asserts.issues.next-lucky-number :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (empty? (s/replace (apply issue/solution v) #"[5|3]" ""))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
