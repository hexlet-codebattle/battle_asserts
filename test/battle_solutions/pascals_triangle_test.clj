(ns battle-solutions.pascals_triangle_test
  (:require [clojure.test :refer [deftest]]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn pt [n]
  (vec (nth
         (iterate #(concat [1]
                           (map + % (rest %))
                           [1])
         [1])
        n)))

(deftest test-asserts
    (assert-equal [1] (pt 0))
    (assert-equal [1 1] (pt 1))
    (assert-equal [1 2 1] (pt 2))
    (assert-equal [1 3 3 1] (pt 3))
    (assert-equal [1 4 6 4 1] (pt 4)))
