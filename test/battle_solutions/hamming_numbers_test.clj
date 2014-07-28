(ns battle-solutions.hamming-numbers-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn hamming-numbers
  [num]
  (letfn [(hammings [initial-set]
            (let [current (first initial-set)
                  others (rest initial-set)]
              (lazy-seq (cons current
                              (hammings (into (sorted-set (* current 2)
                                                          (* current 3)
                                                          (* current 5))
                                              others))))))]
    (last (take num (hammings #{1})))))

(deftest test-asserts
  (assert-equal 5 (hamming-numbers 5))
  (assert-equal 36 (hamming-numbers 20))
  (assert-equal 937500 (hamming-numbers 500))
  (assert-equal 51200000 (hamming-numbers 1000)))
