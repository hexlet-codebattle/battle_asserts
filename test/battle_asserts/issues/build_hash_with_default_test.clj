(ns battle-asserts.issues.build-hash-with-default-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.build-hash-with-default :as issue]))

(deftest test-solution
  (is (= {:draft 0 :completed 0} (issue/solution [:draft :completed] 0)))
  (is (= {:one 4 :two 4} (issue/solution [:one :two] 4)))
  (is (= {:one 3 :two 3 :three 3} (issue/solution [:one :two :three] 3))))
