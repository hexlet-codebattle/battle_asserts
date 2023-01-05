(ns battle-asserts.issues.supremum-syracuse
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Find the largest element in the Syracuse sequence."
   :ru "Найдите наибольший элемент в последовательности Сиракузы."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 100)))

(def test-data
  [{:expected 8
    :arguments [6]}
   {:expected 17
    :arguments [11]}
   {:expected 41
    :arguments [27]}])

(defn solution [num]
  (letfn [(gen-seq
            [num]
            (if (= 1 num)
              [num]
              (concat [num]
                      (if (odd? num)
                        (gen-seq (quot (inc (* 3 num))))
                        (gen-seq (quot num 2))))))]
    (apply max (gen-seq num))))
