(ns battle-asserts.issues.find-k-numbers
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Given an unsorted array of integers, find the Kth largest element."
   :ru "Дан неупорядоченный массив целых чисел. Найдите K-й наибольший элемент."})

(def signature
  {:input [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "k" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [n (gen/choose 1 100)
        arr (gen/vector gen/int n)]
    (gen/tuple arr (gen/choose 1 n))))

(def test-data
  [{:expected 5 :arguments [[1 2 3 4 5 6] 2]}
   {:expected 1 :arguments [[1] 1]}
   {:expected 5 :arguments [[5 5 5 5 5] 1]}
   {:expected 3 :arguments [[3 2 1 5 6 4] 4]}])

(defn solution [arr k]
  (nth (sort > arr) (dec k)))
