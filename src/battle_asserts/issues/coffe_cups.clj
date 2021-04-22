(ns battle-asserts.issues.coffe-cups
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training"])

(def description
  {:en "For each of 6 coffee cups you buy, you get a 7th cup free. In total, you get 7 cups. Create a function that takes `n` cups bought and return the total number of cups."
   :ru "За каждые 6 купленных кофейных чашек вы получаете 7-ю чашку бесплатно. В общей сложности, вы получаете 7 чашек. Создай функцию, которая принимает `n` купленных чашек и рассчитает общее количество чашек."})

(def signature
  {:input [{:argument-name "cups" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 5 1000)))

(def test-data
  [{:expected 14 :arguments [12]}
   {:expected 280 :arguments [240]}
   {:expected 969 :arguments [831]}])

(defn solution [cups]
  (+ cups (int (Math/floor (/ cups 6)))))
