(ns battle-asserts.issues.chess-row-color
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Identify the color of a square of the chessboard. Square A1 is black.")

(def signature
  {:input [{:argument-name "row" :type {:name "string"}}
           {:argument-name "column" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/elements ["A" "B" "C" "D" "E" "F" "G" "H"]) (gen/elements (range 1 9))))

(def test-data
  [{:expected "black"
    :arguments ["A" 1]}
   {:expected "black"
    :arguments ["H" 8]}
   {:expected "black"
    :arguments ["D" 4]}
   {:expected "black"
    :arguments ["G" 7]}
   {:expected "white"
    :arguments ["A" 8]}
   {:expected "white"
    :arguments ["H" 1]}
   {:expected "white"
    :arguments ["E" 8]}
   {:expected "white"
    :arguments ["E" 4]}])

(defn solution [letter num]
  (let [letter-nums (zipmap ["A" "B" "C" "D" "E" "F" "G" "H"]
                            (range 1 9))]
    (if (even? (+ num
                  (letter-nums letter)))
      "black"
      "white")))

