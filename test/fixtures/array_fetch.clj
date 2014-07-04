(ns fixtures.array-fetch
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn fetch
  [s index default]
  (let [positive-index (if (> index 0) index (+ (count s) index))]
    (nth s positive-index default)))

(deftest test-asserts
  (let [arr [\a \b \c]]
    (assert-equal \b (fetch arr 1 \d))
    (assert-equal \d (fetch arr 5 \d))
    (assert-equal \c (fetch arr -1 \d))
    (assert-equal \d (fetch arr -5 \d))))
