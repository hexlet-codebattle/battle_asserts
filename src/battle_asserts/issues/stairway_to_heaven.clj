(ns battle-asserts.issues.stairway-to-heaven
  (:require [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "N dicks randomly spread out of 100 stairs, there can be as many dicks as you want on one step. We gotta go down these stairs.
                  Every time you step on a stair-step with dicks, the brat ratio increases by the number of dicks.
                  You can go down one or two steps at a time. Write a function to descend the stairs minimizing the brat ratio.
                  The function receives an array with the number of dicks on each step and returns the minimum brat index.
                  Powered by Eugene Zaytsev.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/vector (gen/choose 0 12) 3 10)))

(def test-data
  [{:expected 0 :arguments [[1 0 1 0 1]]}
   {:expected 0 :arguments [[0 1 0 1]]}
   {:expected 9 :arguments [[1 0 3 5 10 0 11 1]]}
   {:expected 20 :arguments [[0 11 6 8 1 4 10 9]]}
   {:expected 37 :arguments [[9 11 1 5 6 6 5 4 9 12]]}])

(defn iter-step [steps acc]
  (let [len (count steps)]
    (cond
      (zero? len) acc
      (= len 1) acc
      (= len 2) (+ acc (last steps))
      :else
      (let [first-pol (first steps)
            second-pol (second steps)]
        (cond
          (> first-pol second-pol) (iter-step (drop 2 steps) (+ acc second-pol))
          (< first-pol second-pol) (iter-step (drop 1 steps) (+ acc first-pol))
          :else (iter-step (drop 2 steps) (+ acc second-pol)))))))

(defn solution [steps-map]
  (iter-step steps-map 0))
