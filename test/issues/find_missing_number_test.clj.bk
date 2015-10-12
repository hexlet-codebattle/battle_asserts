(ns battle-solutions.find-missing-number-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn find-missing-number
  [coll]
  (let [n (inc (count coll))
        sum-seq (* (/ (inc n)
                      2)
                   n)]
    (->> coll
         (reduce +)
         (- sum-seq)
         (int))))

(deftest test-asserts
  (assert-equal 1 (find-missing-number []))
  (assert-equal 1 (find-missing-number [2, 3, 4, 5]))
  (assert-equal 2 (find-missing-number [1, 3, 4, 5]))
  (assert-equal 4 (find-missing-number [1, 2, 3, 5])))
