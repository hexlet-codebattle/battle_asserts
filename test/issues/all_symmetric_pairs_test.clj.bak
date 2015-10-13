(ns battle-solutions.all-symmetric-pairs-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn has-pair? [h  pair]
  (let [k (first pair) v (last pair)]
    (and
     (contains? h v)
     (= (get h v) k))))

(defn contains-key-or-value? [h pair]
  (let [k (first pair) v (last pair)]
    (or
     (contains? h v)
     (contains? h k))))

(defn find-symmetric_pairs [array]
  (let [h (into {} array)]
    (->>
     h
     (reduce
      #(if (and (has-pair? h %2) (not (contains-key-or-value? %1 %2)))
         (assoc %1 (first %2) (last %2))
         %1)
      {})
     (into []))))

(deftest test-asserts
  (assert-equal [[30 40] [5 10]] (find-symmetric_pairs [[11 20] [30 40] [5 10] [40 30] [10 5]]))
  (assert-equal [] (find-symmetric_pairs [[11 20] [35 40] [5 10] [45 30] [10 6]]))
  (assert-equal [[11 20]] (find-symmetric_pairs [[11 20] [20 11] [5 10] [45 30] [10 6]])))
