(ns battle-solutions.flatten-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(deftest test-asserts
  (assert-equal [1 2 {:a 'b'}] (flatten [1 [2 [{:a 'b'}]]]))
  (assert-equal [1 2 3 4 5 6 7 8] (flatten [1 [2 3 [4 5 [6 7]]] 8]))
  (assert-equal [] (flatten []))
  (assert-equal [1 2 3 4] (flatten [[1 2] [3 4]])))
