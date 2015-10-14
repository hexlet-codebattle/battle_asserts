(ns battle-asserts.issues.count-pair-with-difference-equal-to-k
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Given an integer array and a positive integer k, count all distinct pairs with difference equal to k.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int) gen/pos-int))

(defn make-pair [value array]
  (into []
        (zipmap array
                (repeat (count array) value))))

(defn solution [arr k]
  (->>
   arr
   (reduce-kv #(concat %1 (make-pair %3 (subvec arr (inc %2)))) [])
   (filter #(= k (Math/abs (apply - %))))
   distinct
   count))
