(ns battle-asserts.issues.sublist
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given two lists, determine whether the first list is
                 contained within the second list, whether the second list is contained within
                 the first list, whether both lists are contained within each other or whether none
                 of these are true.
                 A = [3, 4], B = [1, 2, 3, 4, 5], A is sublist of B
                 A = [1, 2, 3], B = [1, 2, 3], A is equal B
                 A = [1, 2, 3, 4, 5], B = [2, 3, 4], A is superlist of B
                 A = [1, 2, 4], B = [1, 2, 3, 4, 5], A is unequal B]")

(def signature
  {:input  [{:argument-name "arr1" :type {:name "integer"}}
            {:argument-name "arr2" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(superlist [a]
            (rand-nth (partition (rand-int (count a)) 1 a)))
          (sublist [a]
            (concat (repeatedly (rand-int 4) #(rand-int 10))
                    a
                    (repeatedly (rand-int 4) #(rand-int 10))))]
    (gen/bind (gen/vector gen/small-integer 4 8)
              #(gen/tuple (gen/return %) (gen/one-of [(gen/return (superlist %))
                                                      (gen/return (sublist %))
                                                      (gen/return %)
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
