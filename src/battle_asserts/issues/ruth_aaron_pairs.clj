(ns battle-asserts.issues.ruth-aaron-pairs
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Check if a given number is a Ruth–Aaron pair.
        A Ruth–Aaron pair consists of two consecutive integers (e.g. 714 and 715) for
        which the sums of the prime factors of each integer are equal.
        When calculating the sum, take into account the repeating factors.
        If a number cannot be decomposed into prime multipliers, consider the factorization of this number to be 0."
   :ru "Проверьте, является ли переданное число и следующее за ним, парой Рута — Аарона.
        Пара Рута — Аарона состоит из двух последовательных целых чисел (например 714 и 715)
        суммы простых множителей которых равны.
        При подсчете суммы учитывайте повторение множителей.
        Если число нельзя разложить на простые множители, то считайте факторизацию этого числа равной 0."})

(def ruth-aaron-pairs [[5 6] [8 9] [15 16] [77 78] [125 126] [714 715] [948 949] [1330 1331] [1520 1521]
                       [1862 1863] [2491 2492] [3248 3249] [4185 4186] [4191 4192] [5405 5406] [5560 5561]
                       [5959 5960] [6867 6868] [8280 8281] [8463 8464] [10647 10648] [12351 12352] [14587 14588]
                       [16932 16933] [17080 17081] [18490 18491] [20450 20451] [24895 24896] [26642 26643] [26649 26650]])

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 2 30000)))

(def test-data
  [{:expected true :arguments [8]}
   {:expected true :arguments [5]}
   {:expected true :arguments [77]}
   {:expected true :arguments [125]}
   {:expected false :arguments [20]}])

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

(defn solution [num]
  (let [next-num (inc num)]
    (and (= (inc num) next-num)
         (= (apply + (prime-factorization num))
            (apply + (prime-factorization next-num))))))
