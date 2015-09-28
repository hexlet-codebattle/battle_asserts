(ns battle-solutions.array-interleave-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn interleave
  [arr & args]
  (if (nil? args)
    arr
    (sort
     #(compare (read-string (str %1)) (read-string (str %2)))
     (vec (flatten (concat arr (vec args)))))))

(deftest test-asserts
  (let [arr [1 3 5]]
    (assert-equal [1 2 3 4 5 6] (interleave arr 2 4 6))
    (assert-equal [1 2 3 4 5] (interleave arr [2 4]))
    (assert-equal [1 "2" 3 "4" 5] (interleave arr "2" "4"))
    (assert-equal [2 4 6] (interleave [] 2 4 6))
    (assert-equal [1 3 5] (interleave arr))))
