(ns battle-asserts.issues.array-fetch-test
  (:require [clojure.test :refer :all]
            [clojure.test.check.properties :as prop :include-macros true]
            [clojure.test.check.clojure-test :as ct :include-macros true]
            [battle-asserts.issues.array-fetch :as issue]))

(ct/defspec test-solution
  20
  (prop/for-all [v (issue/arguments-generator)]
                (= (apply issue/solution v)
                   (flatten v))))

(deftest test-solution
  (let [arr [\a \b \c]]
    (is (= \b (issue/solution arr 1 \d)))
    (is (= \d (issue/solution arr 5 \d)))
    (is (= \c (issue/solution arr -1 \d)))
    (is (= \d (issue/solution arr -5 \d)))))

