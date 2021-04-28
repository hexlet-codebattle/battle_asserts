(ns battle-asserts.issues.equality-count
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description
  {:en "Create a function that takes array of three integers and returns the amount of integers which are of equal value. Note: Function must return 0, 2 or 3."
   :ru "Создайте функцию которая принимает три числа и возвращает количество чисел, которые равны. Примечание: функция должна возвращать 0, 2 или 3."})

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 1 5) 3)))

(def test-data
  [{:expected 0 :arguments [[1 2 3]]}
   {:expected 2 :arguments [[1 1 3]]}
   {:expected 3 :arguments [[3 3 3]]}])

(defn solution [numbers]
  (let [equal-size (count (set numbers))]
    (if (= equal-size 3)
      0
      (- 4 equal-size))))
