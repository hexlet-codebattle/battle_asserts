(ns battle-asserts.issues.rock-scissors-paper
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections hash-maps"])

(def description
  {:en "Count your score in a game of rock-paper-scissors. 
        You are given two arrays: your moves and your opponent’s moves. 
        You get 1 point for a win, -1 for a loss, and 0 for a draw. 
        Let me remind you that according to the rules:
        - rock beats scissors
        - scissors beat paper
        - paper beats rock."
   :ru "Посчитайте свой счет в игре камень-ножницы-бумага.
        Вам даны два массива: ваши ходы и ходы противника.
        За победу вы получаете 1 очко, за поражение -1 и 0 за ничью.
        Напомню, что по правилам:
        - камень бьет ножницы
        - ножницы бьют бумагу
        - бумага бьет камень."})

(def signature
  {:input  [{:argument-name "your-moves" :type {:name "array" :nested {:name "string"}}}
            {:argument-name "op-moves" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [lenght (inc (rand-int 10))
        moves ["r" "s" "p"]]
    (gen/tuple (gen/vector (gen/elements moves) lenght)
               (gen/vector (gen/elements moves) lenght))))

(def test-data
  [{:expected 3
    :arguments [["r" "s" "p"] ["s" "p" "r"]]}
   {:expected -3
    :arguments [["p" "p" "p"] ["s" "s" "s"]]}
   {:expected 0
    :arguments [["r" "s" "p"] ["r" "s" "p"]]}
   {:expected -3
    :arguments [["p" "r" "s" "r" "s"] ["s" "p" "s" "p" "s"]]}])

(defn solution [your-moves op-moves]
  (let [rules {"r" {"s" 1
                    "r" 0
                    "p" -1}
               "s" {"s" 0
                    "r" -1
                    "p" 1}
               "p" {"s" -1
                    "r" 1
                    "p" 0}}
        pairs (mapv vector your-moves op-moves)]
    (reduce +
            (for [key-pair pairs]
              (get-in rules key-pair)))))
