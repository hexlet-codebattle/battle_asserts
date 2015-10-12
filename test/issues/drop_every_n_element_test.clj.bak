(ns battle-solutions.drop-every-n-element-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn drop-every [n coll]
  (keep-indexed #(if-not (= (mod (inc %1) n)
                            0) %2)
                coll))

(deftest test-asserts
  (assert-equal '("a" "b" "d" "e" "g" "h" "k") (drop-every 3 '("a" "b" "c" "d" "e" "f" "g" "h" "i" "k")))
  (assert-equal [0 2 4 6 8] (drop-every 2 [0 1 2 3 4 5 6 7 8 9]))
  (assert-equal [0 1 2 3] (drop-every 5 [0 1 2 3]))
  (assert-equal [] (drop-every 1 [0 1 2 3 4 5 6 7])))
