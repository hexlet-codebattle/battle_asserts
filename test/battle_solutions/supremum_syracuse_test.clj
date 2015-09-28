(ns battle-solutions.supremum-syracuse-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn supremum-syracuse
  [num]
  (letfn [(gen-seq
            [num]
            (if (= 1 num)
              [num]
              (concat [num]
                      (if (odd? num)
                        (gen-seq (+ 1 (* 3 num)))
                        (gen-seq (/ num 2))))))]
    (apply max (gen-seq num))))

(deftest test-asserts
  (assert-equal 16 (supremum-syracuse 6))
  (assert-equal 52 (supremum-syracuse 11))
  (assert-equal 9232 (supremum-syracuse 27)))
