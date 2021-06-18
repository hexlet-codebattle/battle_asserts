(ns battle-asserts.issues.fibonacci
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math" "recursion"])

(def description
  {:en "Return the N'th item in the Fibonacci sequence Hint: The first item in the sequence is 0."
   :ru "Верните `n`-ый элемент последовательности Фиббоначи. Подсказка: первый элемент последовательности равен 0."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 20)))

(def test-data
  [{:expected 13
    :arguments [7]}
   {:expected 0
    :arguments [0]}
   {:expected 1
    :arguments [1]}
   {:expected 3
    :arguments [4]}
   {:expected 55
    :arguments [10]}])

(defn solution [number]
  (condp = number
    0 0
    1 1
    (+
     (solution (dec number))
     (solution (- number 2)))))
