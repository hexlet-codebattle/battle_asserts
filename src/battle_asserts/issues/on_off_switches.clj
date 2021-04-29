(ns battle-asserts.issues.on-off-switches
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training"])

(def description
  {:en "Create a function that returns how many possible outcomes can come from a certain number of switches (on / off)."
   :ru "Создайте функцию, которая возвращает возможное количество комбинаций для переданного количества переключателей."})

(def signature
  {:input  [{:argument-name "switches" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 2 :arguments [1]}
   {:expected 8 :arguments [3]}
   {:expected 1024 :arguments [10]}])

(defn arguments-generator []
  (gen/tuple (gen/choose 1 15)))

(defn solution [switches]
  (int (Math/pow 2 switches)))
