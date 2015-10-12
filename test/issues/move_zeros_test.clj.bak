(ns battle-solutions.move-zeros-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn move-zeros [array]
  (concat
   (filter #(not (zero? %)) array)
   (filter zero? array)))

(deftest test-asserts
  (assert-equal [1 3 12 0 0] (move-zeros [0 1 0 3 12]))
  (assert-equal [4 3 5 0 0 0] (move-zeros [4 0 3 0 5 0]))
  (assert-equal [4 3 5 0 0 0] (move-zeros [0 0 0 4 3 5])))
