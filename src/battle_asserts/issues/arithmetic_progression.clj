;; Moved to modern repository
(ns battle-asserts.issues.arithmetic-progression
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math" "recursion"])

(def description
  {:en "Calculate sum of arithmetic progression from 1 to `n`."
   :ru "Рассчитайте сумму членов арифметической прогрессии от 1 до `n`,"})

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1 30)))

(def test-data
  [{:expected 1 :arguments [1]}
   {:expected 15 :arguments [5]}
   {:expected 1275 :arguments [50]}])

(defn arithmetic-progression [n]
  (if (zero? n)
    0
    (+ n (arithmetic-progression (dec n)))))

(defn solution [n]
  (arithmetic-progression n))
