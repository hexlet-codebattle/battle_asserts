(ns battle-asserts.issues.integer-difference
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Find how many pairs X, Y there are in the array, such that abs(X-Y) is equal to the first argument.")

(defn arguments-generator []
  (letfn [(atleast-one-pair []
            (gen/bind (gen/vector gen/int 2 8)
                      #(gen/tuple (gen/return (Math/abs (- (first %)
                                                           (last %))))
                                  (gen/shuffle %))))]
    (gen/one-of [(gen/tuple gen/pos-int (gen/vector gen/int))
                 (atleast-one-pair)])))

(def test-data
  [{:expected 3
    :arguments [4 [1 1 5 6 9 16 27]]}
   {:expected 4
    :arguments [2 [1 1 3 3]]}])

(defn solution [differ, nums]
  (->>
   nums
   (reduce-kv #(let [next-elements (drop (inc %2) nums)]
                 (concat %1 (pmap vector
                                  next-elements
                                  (repeat (count next-elements) %3))))
              [])
   (pmap #(- (first %)
             (second %)))
   (filter #(= differ %))
   count))
