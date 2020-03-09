(ns battle-asserts.issues.ruth-aaron-pairs
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Check if a given pair of numbers is a Ruth–Aaron pair. 
                  A Ruth–Aaron pair consists of two consecutive integers (e.g. 714 and 715) for 
                  which the sums of the prime factors of each integer are equal. 
                  When calculating the sum, take into account the repeating factors.")

(def ruth-aaron-pairs [[5 6] [8 9] [15 16] [77 78] [125 126] [714 715] [948 949] [1330 1331] [1520 1521]
                       [1862 1863] [2491 2492] [3248 3249] [4185 4186] [4191 4192] [5405 5406] [5560 5561]
                       [5959 5960] [6867 6868] [8280 8281] [8463 8464] [10647 10648] [12351 12352] [14587 14588]
                       [16932 16933] [17080 17081] [18490 18491] [20450 20451] [24895 24896] [26642 26643] [26649 26650]])

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/elements ruth-aaron-pairs)
                          (gen/bind gen/nat #(gen/return [%, (inc %)]))])))

(def test-data
  [{:expected true
    :arguments [[8 9]]}
   {:expected true
    :arguments [[5 6]]}
   {:expected true
    :arguments [[77 78]]}
   {:expected false
    :arguments [[20 21]]}])

(defn- factorization [value [first-prime & rest-primes :as primes]]
  (cond
    (> 2 value) '()
    (zero? (mod value first-prime)) (cons first-prime (lazy-seq (factorization (quot value first-prime) primes)))
    :else (recur value rest-primes)))

(defn- primes-up-to [value]
  (take-while #(>= value %)
              (iterate #(.nextProbablePrime (biginteger %)) 2)))

(defn prime-factorization [value]
  (factorization value (primes-up-to value)))

(defn solution [[value1 value2]]
  (and (= (inc value1) value2)
       (= (apply + (prime-factorization value1))
          (apply + (prime-factorization value2)))))
