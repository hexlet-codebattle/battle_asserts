(ns battle-asserts.issues.levenshtein-distance
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :hard)

(def description "Calculate the Levenshtein distance.")

(def signature
  {:input  [{:argument-name "s1" :type {:name "array" :nested {:name "string"}}}
            {:argument-name "s2" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "integer"}}})

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
  (letfn [(cell-value [same-char? prev-row cur-row col-idx]
            (min (inc (nth prev-row col-idx))
                 (inc (last cur-row))
                 (+ (nth prev-row (dec col-idx)) (if same-char? 0 1))))]
    (loop [row-idx 1
           max-rows (inc (count seq2))
           prev-row (range (inc (count seq1)))]
      (if (= row-idx max-rows)
        (last prev-row)
        (let [ch2 (nth seq2 (dec row-idx))
              next-prev-row (reduce (fn [cur-row i]
                                      (let [same-char? (= (nth seq1 (dec i)) ch2)]
                                        (conj cur-row (cell-value same-char?
                                                                  prev-row
                                                                  cur-row
                                                                  i))))
                                    [row-idx] (range 1 (count prev-row)))]
          (recur (inc row-idx) max-rows next-prev-row))))))
