(ns battle-solutions.domino-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn domino [x]
  (letfn [(rat [x]
            (+ (reduce + (range (+ x 1)))
               (* x (+ x 1))))]
    (->> (range (+ x 1))
         (map #(rat %))
         (reduce +))))

(deftest test-asserts
  (assert-equal 0  (domino 0))
  (assert-equal 3  (domino 1))
  (assert-equal 12 (domino 2))
  (assert-equal 30 (domino 3)))
