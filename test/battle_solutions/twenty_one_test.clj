(ns battle-solutions.twenty-one-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn twenty-one? [n]
  (= (reduce + n) 21))

(deftest test-asserts
  (assert (twenty-one? '(3 4 5 6 3)))
  (assert (twenty-one? '(11 10)))
  (assert (not (twenty-one? '(3 11 10))))
  (assert (not (twenty-one? '(10 10)))))
