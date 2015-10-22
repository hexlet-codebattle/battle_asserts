(ns battle-asserts.issues.years-since-millennium-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.years-since-millennium :as issue]
            [clojure.test.check.generators :as gen]))

(ct/defspec test-solution
  20
  (prop/for-all [v (gen/tuple (gen/resize 10000 gen/pos-int))]
                (= (apply issue/solution v)
                   (mod (first v) 1000))))
