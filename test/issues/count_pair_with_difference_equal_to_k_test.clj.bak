(ns battle-solutions.count-pair-with-difference-equal-to-k-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn make-pair [value array]
  (into []
        (zipmap array
                (repeat (count array) value))))

(defn count-pairs [arr k]
  (->>
   arr
   (reduce-kv #(concat %1 (make-pair %3 (subvec arr (inc %2)))) [])
   (filter #(= k (Math/abs (apply - %))))
   distinct
   count))

(deftest test-asserts
  (assert-equal 2 (count-pairs [1 5 3 4 2] 3))
  (assert-equal 5 (count-pairs [8 12 16 4 0 20] 4))
  (assert-equal 7 (count-pairs [1 4 3 0 2 5 7 8 9 6] 3))
  (assert-equal 3 (count-pairs [1 2 3 4 4 2 2 1] 0)))
