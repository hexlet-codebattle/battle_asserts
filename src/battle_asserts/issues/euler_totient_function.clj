(ns battle-asserts.issues.euler-totient-function
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :hard)

(def description "Two numbers are coprime if their greatest common divisor equals 1.
                 Euler's totient function f(x) is defined as the number of positive integers less than x which are coprime to x.")

(defn arguments-generator []
  (gen/tuple gen/pos-int))

(defn solution [num]
  (let [prime-factors (filter #(= 0 (rem num %))
                              (take-while (partial > (/ (+ 1 num) 2))
                                          (iterate #(.nextProbablePrime (biginteger %)) 2)))]
    (if (.isProbablePrime (biginteger num) 5)
      (- num 1)
      (* num (reduce * (map #(- 1 (/ 1 %))
                            prime-factors))))))
