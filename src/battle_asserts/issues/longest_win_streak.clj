(ns battle-asserts.issues.longest-win-streak
  (:require [clojure.test.check.generators :as gen]
            [faker.name :as fk-name]))

(def level :easy)

(def tags ["collections" "strings"])

(def description
  {:en "Two friends play Codebattle and record the winner of each round.
        Find the name of the player with the longest winning streak.
        If the winning series is equal, return Draw!."
   :ru "Двое друзей играют в Codebattle и записывают победителя каждого раунда.
        Найдите имя игрока с самой длинной выигрышной серией.
        Если серии побед равны, верните Draw!."})

(def signature
  {:input [{:argument-name "winners" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (repeatedly 2 #(fk-name/first-name))) 4 10)))

(def test-data
  [{:expected "Alice"
    :arguments [["Bob" "Bob" "Alice" "Alice" "Alice" "Bob" "Bob" "Alice"]]}
   {:expected "Bob"
    :arguments [["Bob" "Alice" "Bob" "Bob"]]}
   {:expected "Draw!"
    :arguments [["Bob" "Alice" "Alice" "Bob" "Bob"]]}])

(defn solution [winners]
  (let [win-streaks (partition-by identity winners)
        maximum (apply max (map count win-streaks))
        max-streaks (filter (fn [x] (= maximum (count x))) win-streaks)]
    (if (> (count max-streaks) 1)
      "Draw!"
      (first (flatten max-streaks)))))
