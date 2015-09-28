(ns battle-solutions.twenty-one-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-twenty-one [n]
  (= (reduce + n) 21))

(deftest test-asserts
  (assert (is-twenty-one '(3 4 5 6 3)))
  (assert (is-twenty-one '(11 10)))
  (assert (not (is-twenty-one '(3 11 10))))
  (assert (not (is-twenty-one '(10 10)))))
