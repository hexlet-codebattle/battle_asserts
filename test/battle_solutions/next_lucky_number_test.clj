(ns battle-solutions.next-lucky-number-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn prev-digits [prev-digits owerflow]
  (if (zero? owerflow)
    prev-digits
    (repeat (count prev-digits) 3)))

(defn next-lucky-number [number]
  (Integer.
   (apply str
          (loop [n number, owerflow 0, res '()]
            (let [integer (quot n 10)
                  remnant (+ owerflow (rem n 10))
                  cur-digit (if (<= 4 remnant 5) 5 3)
                  cur-overflow (if (> remnant 5) 1 0)]
              (if (and (zero? integer) (zero? cur-overflow))
                (conj (prev-digits res owerflow) cur-digit)
                (recur integer cur-overflow (conj res cur-digit))))))))

(deftest test-asserts
  (assert-equal 5 (next-lucky-number 4))
  (assert-equal 53 (next-lucky-number 48))
  (assert-equal 333 (next-lucky-number 130))
  (assert-equal 333 (next-lucky-number 98))
  (assert-equal 3333 (next-lucky-number 949))
  (assert-equal 353 (next-lucky-number 339)))
