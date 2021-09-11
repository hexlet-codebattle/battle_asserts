(ns battle-asserts.issues.garden-help)

(def level :medium)

(def tags ["collections" "math"])

(def disabled true)

(def description
  {:en "Yaroslav helps his grandmother plant carrots in the seedbed. The seedbed that Yaroslav is planting is an infinite line. At each integer coordinate you can plant a carrot. Grandmother has already planted `N` carrots in `x` cells. However, grandmother thinks she hasn't planted carrots densely enough, and asks Yaroslav to count how many more cells can be planted. Carrots have a peculiarity - they do not grow if they are planted too sparsely. Grandmother wants the distance from the additional planting to the sown cell to be equal to `distance'. Grandmother realizes that there are infinitely many planting options, so she asks Yaroslav to count only the cells between other planted cells. Yaroslav's grandmother is frugal and does not plant more than one carrot per cell. Create a fuction that takes an array of `cells` from an increasing sequence of `X,, X,,, ... X, `n` numbers, `X`,- coordinate of the seeded cell and `distance` - the minimum distance the grandmother expects. We need to find the number of possible seedings."
   :ru "Ярослав помогает бабушке садить морковку на грядке. Грядка, которую засевает Ярослав, представляет собой бесконечную прямую. В каждой целочисленной координате можно посадить морковку. Бабушка уже посадила `N` морковок в `x` ячеек. Однако бабушка считает, что недостаточно плотно засеяла морковку, и просит Ярослава посчитать сколько ячеек возможно еще засеять. У морковки есть особенность, она не растет, если посажена слишком редко. Бабушка хочет, чтобы расстояние от дополнительной посадки до засеянной ячейки было равно `distance`. Бабушка понимает, что вариантов посадки бесконечно много, поэтому просит Ярослава посчитать только ячейки между другими засеянными ячейками. Бабушка у Ярослава экономная и не садит более одной морковки в ячейку. Создайте фугкцию, которая принимает массив `cells` из возрастающей последовательности `Х,, Х,,, ... Х`, `n` чисел, `Х`,- координата засеянной ячейки и `distance` - минимальное расстояние которое ожидает бабушка. Нужно найти количество возможных вариантов посадки."})

(def signature
  {:input  [{:argument-name "cells" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "distance" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 2 :arguments [[4 8 11 18 19] 2]}
   {:expected 4 :arguments [[-3 2 9 16] 3]}])
