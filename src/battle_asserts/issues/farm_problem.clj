(ns battle-asserts.issues.farm-problem
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training" "games"])

(def description
  {:en "A farmer is asking you to tell him how many legs can be counted among all his animals.
        The farmer breeds three species:
        `chickens = 2 legs`
        `cows = 4 legs`
        `pigs = 4 legs`
        The farmer has counted his animals and he gives you a subtotal for each species.
        You have to implement a function that returns the total number of legs of all the animals."
   :ru "Фермер просит вас посчитать сколько ног у всех его животных.
        Фермер разводит три вида:
        `курицы = 2 ноги`
        `коровы = 4 ноги`
        `свиньи = 4 ноги`
        Фермер посчитал своих животных и говорит вам, сколько их каждого вида.
        Вы должны написать функцию, которая возвращает общее число ног всех животных."})

(def signature
  {:input [{:argument-name "chickens" :type {:name "integer"}}
           {:argument-name "cows" :type {:name "integer"}}
           {:argument-name "pigs" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/choose 2 10)
             (gen/choose 2 10)
             (gen/choose 2 10)))

(def test-data
  [{:expected 36 :arguments [2 3 5]}
   {:expected 22 :arguments [1 2 3]}
   {:expected 50 :arguments [5 2 8]}])

(defn solution
  [chickens cows pigs]
  (+ (* chickens 2) (* cows 4) (* pigs 4)))
