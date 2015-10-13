(ns battle-solutions.pythagorean-triple-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn square [i]
  (* i i))

(defn pythagorean-triple
  [arr]
  (let [x (->> arr
               sort
               reverse)]
    (= (square (first x))
       (+ (square (first (rest x)))
          (square (last (rest x)))))))

(deftest test-asserts
  (assert-equal true (pythagorean-triple [3 4 5]))
  (assert-equal false (pythagorean-triple [8 9 7]))
  (assert-equal true (pythagorean-triple [12 5 13]))
  (assert-equal false (pythagorean-triple [8 3 6])))
