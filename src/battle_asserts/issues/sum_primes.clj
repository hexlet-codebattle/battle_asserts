(ns battle-asserts.issues.sum-primes
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Return the sum of all prime numbers from 2 up to a given number.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 10 1000)))

(def test-data
  [{:expected 4227
    :arguments [200]}
   {:expected 76127
    :arguments [1000]}])

(defn solution [num]
  (reduce + (take-while (partial > num)
                        (iterate #(.nextProbablePrime (biginteger %)) 2))))
