(ns battle-asserts.issues.integer-difference
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Find how many pairs X, Y there are in the array, such that abs(X-Y) is equal to the first argument.")

(def signature
  {:input  [{:argument-name "differ" :type {:name "integer"}}
            {:argument-name "b" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(atleast-one-pair []
            (gen/bind (gen/vector gen/small-integer 2 8)
                      #(gen/tuple (gen/return (Math/abs (- (first %)
                                                           (last %))))
                                  (gen/shuffle %))))]
    (gen/one-of [(gen/tuple gen/nat (gen/vector gen/small-integer))
                 (atleast-one-pair)])))

(def test-data
  [{:expected 3
    :arguments [4 [1 1 5 6 9 16 27]]}
   {:expected 4
    :arguments [2 [1 1 3 3]]}
   {:expected 3
    :arguments [12 [6 6 2 -11 9 -3 9 12 0 -11 7]]}
   {:expected 3
    :arguments  [7 [1 5 -2 2 -5 7 -2]]}
   {:expected 2
    :arguments [1 [-1 1 0]]}
   {:expected 4
    :arguments [3 [-2 0 -2 3 3 1]]}])

(defn solution [differ nums]
  (->>
   nums
   (reduce-kv #(let [next-elements (drop (inc %2) nums)]
                 (concat %1 (map vector
                                 (cycle [%3])
                                 next-elements)))
              [])
   (map #(Math/abs (apply - %)))
   (filter #(= differ %))
   count))
