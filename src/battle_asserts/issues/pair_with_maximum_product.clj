(ns battle-asserts.issues.pair-with-maximum-product
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array with both positive and negative integers, return a pair with the largest product.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int 2 25)))

(def test-data
  [{:expected [6 7]
    :arguments [[1 4 3 6 7 0]]}
   {:expected [-4 -5]
    :arguments [[-1 -3 -4 2 0 -5]]}
   {:expected [-4 -3]
    :arguments [[-1 -2 -4 -3 0 4 3 2 1]]}
   {:expected [-3 -3]
    :arguments [[-3 -3 3 3]]}])

(defn product [array]
  (apply * array))

(defn make-pair [first-array second-array]
  (map vector
       first-array
       second-array))

(defn solution [array]
  (->>
   array
   (reduce-kv #(let [next-elements (drop (inc %2) array)]
                 (concat %1 (make-pair (cycle [%3])
                                       next-elements)))
              [])
   reverse
   (apply max-key product)))
