(ns battle-asserts.issues.group-multiple-by-occurence
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an unsorted array with repetitions, the task is to group multiple occurrence of individual elements.
                 The grouping should happen in a way that the order of first occurrences of all elements is maintained.")

(defn arguments-generator []
  (letfn [(repeat-rand [value]
            (repeat (first (gen/sample (gen/choose 1 5)))
                    value))
          (add-repetition [coll]
                          (reduce #(concat %1 (repeat-rand %2)) [] coll))]
    (gen/tuple (gen/bind (gen/vector gen/int)
                         #(gen/shuffle (add-repetition %))))))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution [array]
  (->>
   array
   (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {})
   (reduce #(concat %1 (repeat (last %2) (first %2))) [])))
