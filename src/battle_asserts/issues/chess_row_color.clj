(ns battle-asserts.issues.chess-row-color
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Identify the color rows of the chessboard. Row A1 - is black.")

(defn arguments-generator []
  (gen/tuple (gen/elements ["A" "B" "C" "D" "E" "F" "G" "H"]) (gen/elements (range 1 9))))

(def test-data
  [{:expected true
    :arguments ["A" 1]}
   {:expected true
    :arguments ["H" 8]}
   {:expected true
    :arguments ["D" 4]}
   {:expected true
    :arguments ["G" 7]}
   {:expected false
    :arguments ["A" 8]}
   {:expected false
    :arguments ["H" 1]}
   {:expected false
    :arguments ["E" 8]}
   {:expected false
    :arguments ["E" 4]}])

(defn solution [letter num]
  (let [letter-nums (zipmap ["A" "B" "C" "D" "E" "F" "G" "H"]
                            (range 1 9))]
    (even? (+ num
              (letter-nums letter)))))

