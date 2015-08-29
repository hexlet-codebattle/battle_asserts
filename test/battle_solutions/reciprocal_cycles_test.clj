(ns battle-solutions.reciprocal-cycles-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn longest-recurring-cycle [x]
  (letfn [(decimal-seq [len]
            (->> (str (with-precision 100 (/ 1M len)))
                 (partition 1) ;; or partition
                 (#(nthnext % 2))
                 (map #(apply str %))
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
      (+ 1 (.indexOf sq (apply max sq))))))

(deftest test-asserts
  (assert-equal 7 (longest-recurring-cycle 10))
  (assert-equal 81 (longest-recurring-cycle 100)))
