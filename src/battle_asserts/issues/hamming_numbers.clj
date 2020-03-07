(ns battle-asserts.issues.hamming-numbers
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "The sequence of Hamming numbers 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27, 30, 32, 36, …
                 consists of all numbers of the form 2^i·3^j·5^k where i, j and k are non-negative integers.
                 Generate n-th number.")

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 500)))

(def test-data
  [{:expected 36
    :arguments [20]}
   {:expected 5
    :arguments [5]}
   {:expected 937500
    :arguments [500]}
   {:expected 51200000
    :arguments [1000]}])

(defn solution [num]
  (letfn [(hammings [initial-set]
            (let [current (first initial-set)
                  others (rest initial-set)]
              (lazy-seq (cons current
                              (hammings (into (sorted-set (* current 2)
                                                          (* current 3)
                                                          (* current 5))
                                              others))))))]
    (last (take num (hammings #{1})))))
