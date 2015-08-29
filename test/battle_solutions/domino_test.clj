(ns battle-solutions.domino-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn domino [x]
  (defn rat [x]
    (defn nu [x] 
      (if (= x 0)
        x
        (+ x (nu (- x 1)))))
    (defn de [x] (* x (+ x 1)))
    (+ (nu x)(de x)))
  (defn sum [it, acc]
    (if (= it 0)
      acc
      (sum (- it 1)(+ acc (rat it)))))
  (sum x 0))   

(deftest test-asserts
  (assert-equal 0  (domino 0))
  (assert-equal 3  (domino 1))
  (assert-equal 12 (domino 2))
  (assert-equal 30 (domino 3)))
