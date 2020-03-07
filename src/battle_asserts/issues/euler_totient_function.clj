(ns battle-asserts.issues.euler-totient-function
  (:require [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "Two numbers are coprime if their greatest common divisor equals 1.
                 Implement the Euler's totient function f(x), which is defined as the number of positive integers less than x which are coprime to x.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple gen/nat))

(def test-data
  [{:expected 1
    :arguments [1]}
   {:expected 4
    :arguments [10]}
   {:expected 16
    :arguments [40]}
   {:expected 60
    :arguments [99]}])

(defn solution [num]
  (let [prime-factors (filter #(zero? (rem num %))
                              (take-while (partial > (/ (inc num) 2))
                                          (iterate #(.nextProbablePrime (biginteger %)) 2)))]
    (if (.isProbablePrime (biginteger num) 5)
      (dec num)
      (* num (reduce * (map #(- 1 (/ 1 %))
                            prime-factors))))))
