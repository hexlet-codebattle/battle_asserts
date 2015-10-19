(ns battle-asserts.issues.drop-every-n-element-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [test-helper :as h]
            [battle-asserts.issues.drop-every-n-element :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (count (apply issue/solution v))
                   (let [coll-size (count (second v))
                         drop-step (first v)]
                     (- coll-size
                        (quot  coll-size drop-step))))))

(h/generate-tests issue/test-data issue/solution)
