(ns battle-asserts.issues.join-url-params-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :as ct]
            [test-helper :as h]
            [clojure.string :as s]
            [battle-asserts.issues.join-url-params :as issue]))

(ct/defspec spec-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (count (s/split (apply issue/solution v) #"[\?|\&]"))
                   (inc (count (second v))))))

(deftest test-solution
  (h/generate-tests issue/test-data issue/solution))
