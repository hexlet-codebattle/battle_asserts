(ns battle-asserts.issues.years-since-millennium-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [battle-asserts.issues.years-since-millennium :as issue]
            [clojure.test.check.generators :as gen]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (gen/tuple (gen/resize 10000 gen/nat))]
                (= (apply issue/solution v)
                   (mod (first v) 1000))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
