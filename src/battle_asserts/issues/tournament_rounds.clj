(ns battle-asserts.issues.tournament-rounds
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math" "training" "codebattle"])

(def description
  {:en "Codebattle players are paired up to play one round of games. There is always one winner from each pair. New rounds are created until one pair of players remains. For example, for 8 players there will be 3 rounds: 1/4 finals, 1/2 finals and final. Create a function that takes the `number of participants` and returns the `number of rounds`."
   :ru "Игроков Codebattle делят на пары для проведения одного раунда игр. Из каждой пары всегда выходит один победитель. Новые раунды создаются до тех пор, пока не останется одна пара игроков. Например, для 8 игроков будет проведено 3 раунда: 1/4 финала, 1/2 финала и финал. Создайте функцию, которая принимает `количество участников` и возвращает `количество раундов`."})

(def signature
  {:input [{:argument-name "participants" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [samples (map #(int (Math/pow 2 %)) (range 1 15))]
    (gen/tuple (gen/elements samples))))

(def test-data
  [{:expected 1 :arguments [2]}
   {:expected 4 :arguments [16]}
   {:expected 5 :arguments [32]}
   {:expected 3 :arguments [8]}])

(defn- log2 [n]
  (/ (Math/log n) (Math/log 2)))

(defn solution [participants]
  (int (log2 participants)))
