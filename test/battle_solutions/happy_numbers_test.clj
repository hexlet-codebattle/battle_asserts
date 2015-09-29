(ns battle-solutions.happy-numbers-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-happy-number [n]
  (loop [n n, seen #{}]
    (cond
      (= n 1)  true
      (seen n) false
      :else
      (recur (->> (str n)
                  (map #(Character/digit % 10))
                  (map #(* % %))
                  (reduce +))
             (conj seen n)))))

(deftest test-asserts
  (assert-true (is-happy-number 7))
  (assert-true (is-happy-number 986543210))
  (assert-true (not (is-happy-number 2)))
  (assert-true (not (is-happy-number 189))))
