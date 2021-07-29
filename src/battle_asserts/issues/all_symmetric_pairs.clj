(ns battle-asserts.issues.all-symmetric-pairs
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def disabled true)

(def tags ["collections"])

(def description
  {:en "Given an array of pairs, find all symmetric pairs in it. If there is no such pairs, return [[-1]]."
   :ru "Дан массив пар, найдите все симметричные пары в нем. Если такие пары отсутсвуют, верните [[-1]]."})

(def signature
  {:input [{:argument-name "pairs" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/vector (gen/choose 0 10) 2) 8 16)))

(def test-data
  [{:expected [[30 40] [5 10]] :arguments [[[11 20] [30 40] [5 10] [40 30] [10 5]]]}
   {:expected [[-1]] :arguments [[[11 20] [35 40] [5 10] [45 30] [10 6]]]}
   {:expected [[11 20]] :arguments [[[11 20] [20 11] [5 10] [45 30] [10 6]]]}])

(defn- has-pair? [h  pair]
  (let [k (first pair) v (last pair)]
    (and
     (contains? h v)
     (= (get h v) k))))

(defn- contains-key-or-value? [h pair]
  (let [k (first pair) v (last pair)]
    (or
     (contains? h v)
     (contains? h k))))

(defn solution [pairs]
  (let [h (into {} pairs)
        result (->>
                h
                (reduce
                 #(if (and (has-pair? h %2) (not (contains-key-or-value? %1 %2)))
                    (assoc %1 (first %2) (last %2))
                    %1)
                 {})
                (into []))]
    (if (empty? result) [[-1]] result)))
