(ns battle-asserts.issues.tic-tac-toe)

(def level :medium)

(def tags ["games"])

(def description
  {:en "Write a function that checks each move of the game tic-tac-toe.
        The function takes the current state of the field as a two-dimensional array.
        If player 'X' won, returns 'X won', player 'O' won - return 'O won'.
        If no one has won yet, return 'Next'.
        If the field is full, but no one has won return 'Game over'"
   :ru "Напишите функцию, которая проверяет каждый ход в игре крестики-нолики.
        Функция принимает текущее состояние поля в виде двумерного массива.
        Если игрок 'X' выиграл - верните 'X won', если выиграл игрок 'O' - верните 'O won'.
        Если никто пока не выиграл - верните 'Next'.
        Если поле полностью заполнено, но никто не выиграл - верните 'Game over'"})

(def signature
  {:input [{:argument-name "field" :type {:name "array" :nested {:name "array" :nested {:name "string"}}}}]
   :output {:type {:name "string"}}})

(def test-data
  [{:expected "X won"
    :arguments [[["X", "O", "X"]
                 ["O", "X", "O"]
                 ["_", "_", "X"]]]}
   {:expected "O won"
    :arguments [[["X", "X", "O"]
                 ["_", "O", "_"]
                 ["O", "X", "O"]]]}
   {:expected "Next"
    :arguments [[["_", "O", "X"]
                 ["X", "_", "_"]
                 ["_", "_", "O"]]]}
   {:expected "Game over"
    :arguments [[["X", "O", "X"]
                 ["O", "X", "X"]
                 ["O", "X", "O"]]]}])
