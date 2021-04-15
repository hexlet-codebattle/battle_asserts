(ns battle-asserts.issues.find-missing-number
  (:require [battle-asserts.utility :as utility]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description "Given a sequence of integers from 1 to N in a random order.
                 One of the numbers is missing (the others are found exactly once).
                 N is not known beforehand. Determine the missing number.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(randomize [] (gen/generate (gen/choose 4 20)))
          (prepare-vector [] (let [coll (range 1 (randomize))
                                   elem-to-drop (rand-int (count coll))
                                   without-elem (vec (utility/drop-nth coll elem-to-drop))] without-elem))
          (prepare-vectors-pool [] (vec (repeatedly 50 prepare-vector)))]
    (gen/tuple (gen/elements (prepare-vectors-pool)))))

(def test-data
  [{:expected 1
    :arguments [[2 3 4 5]]}
   {:expected 2
    :arguments [[1 3 4 5]]}
   {:expected 4
    :arguments [[1 2 3 5]]}])

(defn solution [coll]
  (let [n (inc (count coll))
        sum-seq (* (/ (inc n)
                      2)
                   n)]
    (->> coll
         (reduce +)
         (- sum-seq)
         (int))))
