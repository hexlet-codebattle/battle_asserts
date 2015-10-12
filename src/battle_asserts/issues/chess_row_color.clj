(ns battle-asserts.issues.chess-row-color
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Identify the color rows of the chessboard. Row A1 - is black.")

(defn arguments-generator []
  (gen/tuple (gen/elements ["A" "B" "C" "D" "E" "F" "G" "H"]) (gen/elements (range 1 9))))

(defn solution [letter num]
  (let [letter-nums (zipmap ["A" "B" "C" "D" "E" "F" "G" "H"]
                            (range 1 9))]
    (even? (+ num
              (letter-nums letter)))))
