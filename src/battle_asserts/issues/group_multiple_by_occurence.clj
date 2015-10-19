(ns battle-asserts.issues.group-multiple-by-occurence
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an unsorted array with repetitions, the task is to group multiple occurrence of individual elements.
                 The grouping should happen in a way that the order of first occurrences of all elements is maintained.")

(defn arguments-generator []
  (letfn [(repeat-rand [value]
            (repeat (inc (rand-int 5))
                    value))
          (add-repetition [coll]
                          (reduce #(concat %1 (repeat-rand %2)) [] coll))]
    (gen/tuple (gen/bind (gen/vector gen/int)
                         #(gen/shuffle (add-repetition %))))))

(def test-data
  [{:expected [5 5 3 3 3 1]
    :arguments [[5 3 5 1 3 3]]}
   {:expected [4 4 4 6 6 9 9 2 3 10]
    :arguments [[4 6 9 2 3 4 9 6 10 4]]}
   {:expected [10 10 10 5 3 3 4 1]
    :arguments [[10 5 3 10 10 4 1 3]]}])

(defn solution [array]
  (let [element-occurrence (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {} array)]
    (reduce #(concat %1 (repeat (get element-occurrence %2) %2)) [] array)))

