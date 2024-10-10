(ns battle-asserts.issues.garden-help
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["collections" "math" "games"])

(def description
  {:en "Yaroslav helps his grandmother plant carrots in a single-line seedbed where a carrot can be planted at each
  integer coordinate along the line. Having already planted carrots in cells spaced at various distances along the line,
  Grandmother has learned carrots do not grow well when planted too sparsely and thinks the carrots need to be planted
  more densely to grow. She asks Yaroslav to count how many additional cells can be planted with carrots between the
  current planted cells, given a minimum `distance` between planted cells.
  Create a function that returns the number of additional cells that can be seeded given 1) an integer array of seeded `cells` in an
  increasing sequence of `X,, X,,, ... X,` and 2) a minimum `distance` grandmother expects between planted cells."
   :ru "Ярослав помогает бабушке садить морковку на грядке. Грядка, которую засевает Ярослав, представляет собой бесконечную прямую. В каждой целочисленной координате можно посадить морковку. Бабушка уже посадила `N` морковок в `x` ячеек. Однако бабушка считает, что недостаточно плотно засеяла морковку, и просит Ярослава посчитать сколько ячеек возможно еще засеять. У морковки есть особенность, она не растет, если посажена слишком редко. Бабушка хочет, чтобы расстояние от дополнительной посадки до засеянной ячейки было равно `distance`. Бабушка понимает, что вариантов посадки бесконечно много, поэтому просит Ярослава посчитать только ячейки между другими засеянными ячейками. Бабушка у Ярослава экономная и не садит более одной морковки в ячейку. Создайте функцию, которая принимает массив `cells` из возрастающей последовательности `Х,, Х,,, ... Х`, `n` чисел, `Х`,- координата засеянной ячейки и `distance` - минимальное расстояние которое ожидает бабушка. Нужно найти количество возможных вариантов посадки."})

(def signature
  {:input  [{:argument-name "cells" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "distance" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/fmap (comp vec sort)
                       (gen/vector-distinct gen/small-integer
                                            {:max-elements 15 :min-elements 5}))
             (gen/elements (range 2 5))))

(def test-data
  [{:expected 3 :arguments [[4 8 11 18 19] 2]}
   {:expected 2 :arguments [[-3 2 9 16] 3]}
   {:expected 5 :arguments [[-28 -26 -23 -16 -12 -10 4 8 10 11 13 16 17 23] 3]}
   {:expected 13 :arguments [[-27 -24 -23 -18 -14 -11 2 5 6 7 16 20 22 28] 2]}])

(defn solution [cells distance]
  (loop [idx 0 offset 0 seedings 0]
    (if (= idx (dec (count cells)))
      seedings
      (let [a (nth cells idx)
            offset (+ offset distance)
            b (nth cells (inc idx))]
        (cond (and (< a (+ a offset) b)
                   (<= distance (- b (+ a offset))))
              (recur idx offset (inc seedings))
              :else (recur (inc idx) 0 seedings))))))
