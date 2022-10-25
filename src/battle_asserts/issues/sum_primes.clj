(ns battle-asserts.issues.sum-primes
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Return the sum of all prime numbers from 2 up to a given number, not including this number."
   :ru "Верните сумму всех простых чисел от 2 до переданного числа, не включая само число."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 10 1000)))

(def test-data
  [{:expected 2
    :arguments [3]}
  [{:expected 4227
    :arguments [200]}
   {:expected 76127
    :arguments [1000]}])

(defn solution [num]
  (int (reduce + (take-while (partial > num)
                             (iterate #(.nextProbablePrime (biginteger %)) 2)))))
