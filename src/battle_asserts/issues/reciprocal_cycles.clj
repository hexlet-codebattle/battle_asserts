(ns battle-asserts.issues.reciprocal-cycles
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :hard)

(def tags ["math"])

(def description "A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
  1/2 = 0.5
  1/3 = 0.(3)
  1/4 = 0.25
  1/5 = 0.2
  1/6 = 0.1(6)
  1/7 = 0.(142857)
  1/8 = 0.125
  1/9 = 0.(1)
  1/10 = 0.1
  Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle. Find the value of d < 100 for which 1/d contains the longest recurring cycle in its decimal fraction part.")

(def signature
  {:input [{:argument-name "x" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 99)))

(def test-data
  [{:expected 7 :arguments [10]}
   {:expected 81 :arguments [100]}])

(defn solution [x]
  (letfn [(decimal-seq [len]
            (->> (str (with-precision 100 (/ 1M len)))
                 (partition 1)
                 (#(nthnext % 2))
                 (map s/join)
                 (map #(Integer/valueOf %))))
          (period-length [coll]
            (loop [m {}
                   i 0
                   c coll]
              (and (seq c)
                   (let [k (first c)]
                     (if-let [v (m k)]
                       (- i v)
                       (recur (assoc m k i)
                              (inc i)
                              (next c)))))))]
    (let [sq (replace {nil 0}
                      (map #(period-length (decimal-seq %))
                           (vec (take x (iterate inc 1)))))]
      (inc (.indexOf sq (apply max sq))))))
