(ns battle-asserts.issues.snail-race
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["games"])

(def description
  {:en "Steve and Maurice have racing snails.
        They each have 3, a slow (s), medium (m) and fast (f) one. Although Steve's
        snails are all a bit stronger than Maurice's, Maurice has a trick up his sleeve. His plan is:
        Round 1: `[s, f]` Sacrifice his slowest snail against Steve's fastest.
        Round 2: `[m, s]` Use his middle snail against Steve's slowest.
        Round 3: `[f, m]` Use his fastest snail against Steve's middle.
        Create a function that determines whether Maurice's plan will work if Maurice wins 2/3 games.
        Snails count of each players is 3, snails randomly shuffled in array."
   :ru "У Стива и Мориса есть гоночные улитки.
        У каждого по 3 штуки - медленная (м), средняя (с) и быстрая (б).
        Хотя улитки Стива немного сильнее чем у Мориса, у Мориса есть хитрость. Его план таков:
        Раунд 1: `[м, б]` Принести свою самую медленную улитку в жертву против самой быстрой улитки Стива.
        Раунд 2: `[с, м]` Выставить свою среднюю улитку против медленной улитки Стива.
        Раунд 3: `[б, с]` Выставить свою быструю улитку против средней улитки Стива.
        Создайте функцию которая определяет сработает ли план Мориса, выиграет ли Морис 2 из 3 игр.
        Количество улиток каждого игрока - 3, улитки случайно перемешаны в массиве."})

(def signature
  {:input [{:argument-name "maurice" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "steve" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 1 50) 3) (gen/vector (gen/choose 1 50) 3)))

(def test-data
  [{:expected true :arguments [[3 5 10] [3 4 10]]}
   {:expected false :arguments [[5 6 9] [4 9 10]]}
   {:expected true :arguments [[1 8 9] [2 4 10]]}])

(defn solution [maurice-snails steve-snails]
  (let [sorted-maurice (sort maurice-snails)
        sorted-steve (sort steve-snails)]
    (and (> (nth sorted-maurice 1) (first sorted-steve))
         (> (last sorted-maurice) (nth sorted-steve 1)))))
