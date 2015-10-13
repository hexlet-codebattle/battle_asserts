(ns battle-solutions.segregate-even-and-odd-numbers-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn segregate-even-odd [array]
  (concat (filter even? array)
          (filter odd? array)))

(deftest test-asserts
  (assert-equal [12 34 8 90 45 9 3] (segregate-even-odd [12 34 45 9 8 90 3]))
  (assert-equal [44 54 14 48 1 7 51 23 25 41] (segregate-even-odd [1 7 44 51 54 14 23 25 48 41]))
  (assert-equal [2 4 1 3 5] (segregate-even-odd [1 2 3 4 5]))
  (assert-equal [2 4 8 6 5 9 3] (segregate-even-odd [2 4 5 9 3 8 6])))
