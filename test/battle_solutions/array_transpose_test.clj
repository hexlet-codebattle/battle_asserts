(ns battle-solutions.array-transpose-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn transpose
  [vectors]
  (if (not-empty vectors)
    (apply mapv vector vectors)
    []))

(deftest test-asserts
  (assert-equal [[1 :a] [2 :b] [3 :c]] (transpose [[1 2 3] [:a :b :c]]))
  (assert-equal [[1 3 5] [2 4 6]] (transpose [[1 2] [3 4] [5 6]]))
  (assert-equal [] (transpose [])))
