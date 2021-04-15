(ns battle-asserts.issues.sublist
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description "Given two lists, determine whether the first list is
                 contained within the second list, whether the second list is contained within
                 the first list, whether both lists are contained within each other or whether none
                 of these are true.
                 A = [3, 4], B = [1, 2, 3, 4, 5], A is sublist of B
                 A = [1, 2, 3], B = [1, 2, 3], A is equal B
                 A = [1, 2, 3, 4, 5], B = [2, 3, 4], A is superlist of B
                 A = [1, 2, 4], B = [1, 2, 3, 4, 5], A is unequal B]")

(def signature
  {:input  [{:argument-name "arr1" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "arr2" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(rand-num [num] (gen/generate (gen/choose 1 num)))
          (randomize [] (gen/generate (gen/vector gen/small-integer 3 10)))
          (superlist [a]
            (vec (rand-nth (partition (rand-num (count a)) 1 a))))
          (sublist [a]
            (vec (concat (repeatedly (rand-num 4) #(rand-num 10))
                         a
                         (repeatedly (rand-num 4) #(rand-num 10)))))]
    (let [vect (randomize)]
      (gen/tuple (gen/one-of [(gen/return (superlist vect))
                              (gen/return (sublist vect))
                              (gen/vector gen/small-integer 4 8)])
                 (gen/one-of [(gen/return (superlist vect))
                              (gen/return (sublist vect))
                              (gen/vector gen/small-integer 4 8)])))))

(def test-data
  [{:arguments [[1 2 3] [1 2 3 4 5]] :expected "A is sublist of B"}
   {:arguments [[3 4 5] [1 2 3 4 5]] :expected "A is sublist of B"}
   {:arguments [[3 4] [1 2 3 4 5]] :expected "A is sublist of B"}
   {:arguments [[1 2 3] [1 2 3]] :expected "A is equal B"}
   {:arguments [[1 2 3 4 5] [2 3 4]] :expected "A is superlist of B"}
   {:arguments [[1 2 4] [1 2 3 4 5]] :expected "A is unequal B"}])

(defn solution [a b]
  (cond
    (= a b) "A is equal B"
    (some #{a} (partition (count a) 1 b)) "A is sublist of B"
    (some #{b} (partition (count b) 1 a)) "A is superlist of B"
    :else "A is unequal B"))
