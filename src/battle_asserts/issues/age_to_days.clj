;; Moved to modern repository
(ns battle-asserts.issues.age-to-days
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "date-time"])

(def description
  {:en "Create a function that takes the age in years and returns the age in days. Ignore leap years."
   :ru "Создайте функцию, которая принимает возраст в годах и возвращает возраст в днях. Високосные года не учитываются."})

(def signature
  {:input [{:argument-name "age" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 100)))

(def test-data
  [{:expected 4380 :arguments [12]}
   {:expected 0 :arguments [0]}
   {:expected 8395 :arguments [23]}])

(defn solution [age]
  (* age 365))
