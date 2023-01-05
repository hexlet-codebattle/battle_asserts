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

(defn- sur-num [num]
  (if (= 1 num)
    num
    (if (odd? num)
      (quot (inc (* 3 num)) 2)
      (quot num 2))))

(defn solution [num]
  (let [sur-num-seq (map sur-num (range 1 (inc num)))]
    (apply max sur-num-seq)))
