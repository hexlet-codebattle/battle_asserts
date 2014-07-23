(ns battle-solutions.sum-primes-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn sum-primes
  [num]
  (reduce + (take-while (partial > num)
                        (iterate #(.nextProbablePrime (biginteger %)) 2))))


(deftest test-asserts
  (assert-equal 76127 (sum-primes 1000))
  (assert-equal 4227 (sum-primes 200)))
