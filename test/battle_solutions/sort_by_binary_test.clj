(ns battle-solutions.sort-by-binary-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn binary-count [i]
  (->> i
       Integer/toBinaryString
       (filter #(= % \1))
       count))

(defn sort-by-binary [arr]
  (sort-by binary-count arr))

(deftest test-asserts
  (assert-equal [1 2 4 3] (sort-by-binary [1 2 3 4]))
  (assert-equal [8 9 6 7] (sort-by-binary [9 8 7 6]))
  (assert-equal [64 5 7 255] (sort-by-binary [255 7 5 64]))
  (assert-equal [4 2 5 7] (sort-by-binary [5 4 2 7])))
