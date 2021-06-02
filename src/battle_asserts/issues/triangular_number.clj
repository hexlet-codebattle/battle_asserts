(ns battle-asserts.issues.triangular-number
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math"])

(def description
  {:en "Triangular Number Sequence is generated from a pattern of dots that form a triangle.
        The first 5 numbers of the sequence, or dots, are: `1, 3, 6, 10, 15`
        This means that the first triangle has just one dot, the second one has three dots, the third one has 6 dots and so on.
        Write a function that gives the number of dots with its corresponding triangle number of the sequence."
   :ru "Последовательность треугольных чисел генерируется из шаблона точек образующих треугольник.
        Первые 5 чисел последовательности, или точек - это `1, 3, 6, 10, 15`.
        Это значит что первый треугольник имеет всего одну точку, второй - три точки, третий - 6 и так далее.
        Напишите функцию, которая принимает порядковый номер треугольного числа и возвращает соответствующее ему количество точек."})

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 250)))

(def test-data
  [{:expected 1 :arguments [1]}
   {:expected 21 :arguments [6]}
   {:expected 23220 :arguments [215]}])

(defn solution [n]
  (int (/ (* n (inc n)) 2)))
