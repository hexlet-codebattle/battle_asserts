(ns battle-solutions.missing-number-in-progression-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn find-missing-number-recur [array p-step]
  (if (<= (count array) 2)
    (+ (first array) p-step)
    (let [mid-index (quot (count array) 2)
          mid-element (+ (first array) (* mid-index p-step))
          real-mid-element (get array mid-index)]
      (if (= mid-element real-mid-element)
        (recur (subvec array mid-index (count array)) p-step)
        (recur (subvec array 0 (inc mid-index)) p-step)))))

(defn find-missing-number [array]
  (find-missing-number-recur array (quot (- (last array) (first array)) (count array))))

(deftest test-asserts
  (assert-equal 6 (find-missing-number [2 4 8 10 12 14]))
  (assert-equal 26 (find-missing-number [1 6 11 16 21 31]))
  (assert-equal 5 (find-missing-number [1 3 7 9 11 13]))
  (assert-equal 0 (find-missing-number [1 -1 -2 -3 -4 -5 -6]))
  (assert-equal 8 (find-missing-number [14 11 5 2]))
  (assert-equal -8 (find-missing-number [-14 -12 -10 -6 -4]))
  (assert-equal 11 (find-missing-number [14 8 5])))
