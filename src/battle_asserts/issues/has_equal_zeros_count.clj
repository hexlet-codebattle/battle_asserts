(ns battle-asserts.issues.has-equal-zeros-count
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Calculate the number of zeros is equal whether the two numbers represented in binary?")

(def signature
  {:input  [{:argument-name "a" :type {:name "integer"}}
            {:argument-name "b" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 11) (gen/choose 0 59)))

(def test-data
  [{:expected true :arguments [2 5]}
   {:expected true :arguments [4 9]}
   {:expected true :arguments [8 17]}
   {:expected false :arguments [1 4]}
   {:expected false :arguments [10 11]}])

(defn solution
  [a b]
  (letfn [(zeros-count [n]
            (->> n
                 (Integer/toBinaryString)
                 (filter #(= \0 %))
                 count))]
    (= (zeros-count a)
       (zeros-count b))))
