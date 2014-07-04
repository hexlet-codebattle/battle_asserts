(ns battle-solutions.broken-include-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn include?
  [v value]
  (some #{value} v))

(deftest test-asserts
  (let [s [1 2 4 5 6 7]]
    (assert (include? s 5))
    (assert (not (include? s 10)))))
