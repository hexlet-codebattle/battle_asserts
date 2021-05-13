(ns battle-asserts.issues.missing-third-angle
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "2 out of 3 angles are given in a triangle, in degrees.
        Write a function that classifies the missing angle as \"acute\", \"right\", or \"obtuse\".
        `An acute angle is less than 90 degrees.`
        `A right angle is exactly 90 degrees.`
        `An obtuse angle is greater than 90 degrees (but less than 180 degrees).`
        The sum of angles of any triangle is always 180 degrees."
   :ru "Даны 2 угла треугольника из 3-х, углы даны в градусах.
        Напишите функцию классифицирующую отсутствующий угол как острый - \"acute\", прямой - \"right\" или тупой - \"obtuse\".
        Острый угол меньше 90 градусов.
        Прямой угол равен точно 90 градусам.
        Тупой угол больше 90 градусов (но меньше 180 градусов).
        Сумма углов любого треугольника всегда равна 180 градусам."})

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 0 100) (gen/choose 0 100)))

(def test-data
  [{:expected "obtuse" :arguments [27 59]}
   {:expected "acute" :arguments [100 11]}
   {:expected "right" :arguments [45 45]}])

(defn solution [first second]
  (let [third (- 180 first second)]
    (cond
      (> third 90) "obtuse"
      (< third 90) "acute"
      :else "right")))
