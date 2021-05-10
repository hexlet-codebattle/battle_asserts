(ns battle-asserts.issues.kinetic-energy
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["physics"])

(def description
  {:en "Create a function that calculates kinetic energy with the following formula:
        `KE = 1/2 * m * v^2`
        `m` is mass in kg, `v` is velocity in m/s, `KE` is kinetic energy in J.
        Round answer to the nearest integer."
   :ru "Создайте функцию, которая рассчитывает кинетическую энергию по следующей формуле:
        `KE = 1/2 * m * v^2`
        `m` - это масса в кг, `v` - это скорость в м/с, `KE` - это кинетическая энергия в Дж.
        Округлите ответ до ближайшего целого."})

(def signature
  {:input [{:argument-name "mass" :type {:name "integer"}}
           {:argument-name "velocity" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 60) (gen/choose 1 50)))

(def test-data
  [{:expected 270 :arguments [60 3]}
   {:expected 2250 :arguments [45 10]}
   {:expected 11000 :arguments [55 20]}])

(defn solution [mass velocity]
  (int (Math/round (* 0.5 mass (Math/pow velocity 2)))))
