(ns battle-asserts.issues.celsius-to-kelvin
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["conversion" "physics"])

(def description
  {:en "Сonvert Celsius temperature to Kelvin. Use floor rounding for the result temperature."
   :ru "Создайте функцию, конвертирующую температуру из Цельсия в Кельвины. Полученный результат округлите в меньшую сторону."})

(def signature
  {:input [{:argument-name "temperature" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -500 500)))

(def test-data
  [{:expected 308 :arguments [35]}
   {:expected 261 :arguments [-12]}
   {:expected 273 :arguments [0]}
   {:expected -226 :arguments [-500]}])

(defn solution [temperature]
  (int (+ temperature 273.15)))
