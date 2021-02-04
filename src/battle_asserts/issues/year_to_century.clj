(ns battle-asserts.issues.year-to-century
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description
  {:en "Create a function that takes a year and returns it's corresponding century."
   :ru "Создайте функцию, которая рассчитывает век от полученного года."})

(def signature
  {:input [{:argument-name "year" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 1900 3000)))

(def test-data
  [{:expected 21 :arguments [2005]}
   {:expected 20 :arguments [1950]}
   {:expected 19 :arguments [1900]}
   {:expected 21 :arguments [2055]}])

(defn solution [year]
  (int (Math/ceil (/ year 100))))
