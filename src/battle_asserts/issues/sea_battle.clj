(ns battle-asserts.issues.sea_battle)

(def level :medium)

(def disabled true)

(def tags ["games"])

(def description
  {:en "It is necessary to return the number of ships to the field in the Sea Battle
        game. The field is represented as a two-dimensial array where 1 means part of the ship and can be of different sizes"
   :ru "Необходимо вернуть число кораблей на поле игры Морской бой.
        Поле представлено двумерным массивом, где 1 означает часть корабля, корабли могут быть различного размера."})

(def signature
  {:input [{:argument-name "field" :type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}]
   :output {:type {:name "integer"}}})

(def test-data
  [{:expected 7
    :arguments [[[1 0 1 0 0 0]
                 [1 0 0 0 1 1]
                 [0 0 0 0 0 0]
                 [0 1 1 1 0 1]
                 [0 0 0 0 0 1]
                 [1 1 0 1 0 0]]]}
   {:expected 2
    :arguments [[[0 1 0]
                 [0 0 0]
                 [0 0 1]]]}
   {:expected 5
    :arguments [[[1 0 0 0 1]
                 [0 0 0 0 1]
                 [1 1 1 0 0]
                 [0 0 0 0 1]
                 [1 1 0 0 1]]]}])
