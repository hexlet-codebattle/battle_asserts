(ns battle-asserts.issues.levenshtein-distance
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :hard)

(def description "Calculate the Levenshtein distance.")

(defn arguments-generator []
  (letfn [(rand-char []
            (char (rand-nth (range (int \a) (inc (int \z))))))
          (input []
                 (let [word (faker/word {:lang :en})
                       word-count (count word)
                       possible-distance (rand-int word-count)
                       changes [#(let [insert-index (rand-int (count %))]
                                   (str (subs % 0 insert-index) (rand-char) (subs % insert-index)))
                                #(let [remove-index (rand-int (count %))]
                                   (str (subs % 0 remove-index) (subs % (inc remove-index))))
                                #(let [change-index (rand-int (count %))]
                                   (str (subs % 0 change-index) (rand-char) (subs % (inc change-index))))]]
                   (loop [index possible-distance result word]
                     (if (zero? index)
                       [word result]
                       (recur (dec index) ((rand-nth changes) result))))))]
    (gen/elements (repeatedly 50 input))))

(def test-data
  [{:expected 3
    :arguments ["kitten" "sitting"]}
   {:expected 1
    :arguments ["clojure" "closure"]}
   {:expected 2
    :arguments ["xyx" "xyyyx"]}
   {:expected 6
    :arguments ["" "123456"]}
   {:expected 0
    :arguments [[] []]}
   {:expected 2
    :arguments [[1 2 3 4] [0 2 3 4 5]]}])

(defn solution [seq1 seq2]
  (cond
    (empty? seq1) (count seq2)
    (empty? seq2) (count seq1)
    :else (min
           (+ (if (= (first seq1) (first seq2)) 0 1)
              (#'solution (rest seq1) (rest seq2)))
           (inc (#'solution (rest seq1) seq2))
           (inc (#'solution seq1 (rest seq2))))))
