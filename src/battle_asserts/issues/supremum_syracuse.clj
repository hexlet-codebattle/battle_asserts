(ns battle-asserts.issues.supremum-syracuse
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Find the largest element in the Syracuse sequence.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 100)))

(def test-data
  [{:expected 16
    :arguments [6]}
   {:expected 52
    :arguments [11]}
   {:expected 9232
    :arguments [27]}])

(defn solution [num]
  (letfn [(gen-seq
            [num]
            (if (= 1 num)
              [num]
              (concat [num]
                      (if (odd? num)
                        (gen-seq (inc (* 3 num)))
                        (gen-seq (/ num 2))))))]
    (apply max (gen-seq num))))
