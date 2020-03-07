(ns battle-asserts.issues.missing-number-in-progression
  (:require [battle-asserts.utility :as utility]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array that represents elements of an arithmetic progression in order.
                 One element is missing in the progression. Return the missing number.")

(def signature
  {:input  [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(progression [first-element step size]
            (let [last-element (+ first-element
                                  (* step size))]
              (range first-element last-element step)))
          (random-not-extreme [size]
            (->
             size
             (- 2)
             rand-int
             inc))]
    (gen/tuple (gen/bind (gen/tuple (gen/choose -20 20)
                                    utility/gen-pos-num
                                    (gen/choose 5 10))
                         #(->
                           (apply progression %)
                           (utility/drop-nth (random-not-extreme (last %)))
                           (->> (into []))
                           gen/return)))))

(def test-data
  [{:expected 6
    :arguments [[2 4 8 10 12 14]]}
   {:expected 26
    :arguments [[1 6 11 16 21 31]]}
   {:expected 5
    :arguments [[1 3 7 9 11 13]]}
   {:expected 0
    :arguments [[1 -1 -2 -3 -4 -5 -6]]}
   {:expected 8
    :arguments [[14 11 5 2]]}
   {:expected -8
    :arguments [[-14 -12 -10 -6 -4]]}
   {:expected 11
    :arguments [[14 8 5]]}])

(defn find-missing-number-recur [array p-step]
  (if (<= (count array) 2)
    (+ (first array) p-step)
    (let [mid-index (quot (count array) 2)
          mid-element (+ (first array) (* mid-index p-step))
          real-mid-element (get array mid-index)]
      (if (= mid-element real-mid-element)
        (recur (subvec array mid-index (count array)) p-step)
        (recur (subvec array 0 (inc mid-index)) p-step)))))

(defn solution [array]
  (find-missing-number-recur array (quot (- (last array) (first array)) (count array))))
