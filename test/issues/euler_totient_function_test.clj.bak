(ns battle-solutions.euler-totient-function-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn euler-totient-function
  [num]
  (let [prime-factors (filter #(= 0 (rem num %))
                              (take-while (partial > (/ (+ 1 num) 2))
                                          (iterate #(.nextProbablePrime (biginteger %)) 2)))]
    (if (.isProbablePrime (biginteger num) 5)
      (- num 1)
      (* num (reduce * (map #(- 1 (/ 1 %))
                            prime-factors))))))

(deftest test-asserts
  (assert-equal 1 (euler-totient-function 1))
  (assert-equal 4 (euler-totient-function 10))
  (assert-equal 16 (euler-totient-function 40))
  (assert-equal 60 (euler-totient-function 99)))
