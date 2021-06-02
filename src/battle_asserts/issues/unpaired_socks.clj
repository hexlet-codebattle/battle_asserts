(ns battle-asserts.issues.unpaired-socks
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "\"Where do the socks go?\" - Misha thought after another laundry.
                 As usual, after the washing, he had a lot of socks left without pair, and he had to decide how many new socks to buy.
                 He decided to simplify the task and make a machine that would count socks without pairs.
                 Misha decided to create an algorithm to search for unpaired socks. Help Misha to write an algorithm.
                 The function accepts an array of `n` integers `X`, - where `X` is the color of the sock from 0 to 1000. "
   :ru "«Куда пропадают носки?» — размышлял Миша после очередной стирки. Как обычно,
после стирки у него осталась куча носков без пар, и ему нужно было решить, сколько
новых носков прикупить. Он решил упростить задачу и сделать машинку, которая будет
считать беспарные носки. Однако, прежде чем реализовывать ее в железе, Миша решил
создать алгоритм, позволяющий машине тратить как можно меньше усилий на поиск
непарных носков. Помогите Мише написать алгоритм для машинки.
Функция принимает массив: массив из п целых чисел Х, - где Х, это цвет носка от
0 до 1000. Метод должен вернуть целое число, равное количеству носков не имеющих
пары."})

(def signature
  {:input  [{:argument-name "socks" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 2 :arguments [[19 20 11 9 20 9]]}
   {:expected 0 :arguments [[19 19 11 11]]}
   {:expected 1 :arguments [[19 16 19 11 11]]}])

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 0 1000) 4 20)))

(defn solution [socks]
  (count (filter #(= (last %) 1) (frequencies socks))))

