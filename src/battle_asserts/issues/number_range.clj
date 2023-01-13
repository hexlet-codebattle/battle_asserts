(ns battle-asserts.issues.number-range
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training"])

(def description
  {:en "Create a function that validates whether a number `n` is inclusively within the bounds of `lower` and `upper`."
   :ru "Создайте функцию, которая проверяет, находится ли число `n` в границах между `lower` и `upper` включительно."})

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}
           {:argument-name "lower" :type {:name "integer"}}
           {:argument-name "upper" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 30) (gen/choose 0 30) (gen/choose 0 30)))

(def test-data
  [{:expected true :arguments [3 1 9]}
   {:expected false :arguments [7 1 6]}
   {:expected true :arguments [11 1 20]}
   {:expected false :arguments [4 11 15]}])

(defn solution [num left-border right-border]
  (contains? (set (range left-border (inc right-border))) num))
