(ns battle-asserts.issues.pie-slice
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "Create a function that determines whether or not it's possible to split a pie fairly given these three parameters: Total number of slices. Number of recipients. How many slices each person gets."
   :ru "Создайте функцию, которая проверяет, возможно ли честно разделить пирог по трем параметрам: всего кусков, количество людей, сколько кусков получит каждый человек."})

(def signature
  {:input [{:argument-name "total" :type {:name "integer"}}
           {:argument-name "people" :type {:name "integer"}}
           {:argument-name "each" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 3 40)
             (gen/choose 2 10)
             (gen/choose 1 10)))

(def test-data
  [{:expected true :arguments [15 5 3]}
   {:expected true :arguments [8 3 2]}
   {:expected false :arguments [10 4 4]}
   {:expected true :arguments [26 13 2]}])

(defn solution [total people each]
  (>= total (* people each)))
