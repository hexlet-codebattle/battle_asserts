(ns battle-solutions.pair-with-maximum-product-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn product [array]
  (apply * array))

(defn pair-with-max-product [array]
  (let [sort-array (sort array)
        two-max (take-last 2 sort-array)
        two-min (take 2 sort-array)]
    (if (>
         (product two-min)
         (product two-max))
      two-min
      two-max)))

(deftest test-asserts
  (assert-equal [6 7] (pair-with-max-product [1 4 3 6 7 0]))
  (assert-equal [-5 -4] (pair-with-max-product [-1 -3 -4 2 0 -5]))
  (assert-equal [3 4] (pair-with-max-product [-1 -2 -4 -3 0 4 3 2 1])))
