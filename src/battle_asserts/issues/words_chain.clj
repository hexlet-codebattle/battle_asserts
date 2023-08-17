(ns battle-asserts.issues.words-chain
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def tags ["strings"])

(def description
  {:en "Write a function which takes a sequence of words, and returns true if they can be arranged into one continuous word chain, and false if they cannot.
Example: cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog"
   :ru "Напишите функцию, которая принимает последовательность слов и проверяет, могут ли слова быть организованы в одну непрерывную цепочку слов.
Пример: cat -> cot -> coat -> oat -> hat -> hot -> hog -> dog"})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [words (faker/words {:n 40})]
    (gen/tuple (gen/vector (gen/elements words) 2 8))))

(def test-data
  [{:expected true :arguments [["hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"]]}
   {:expected false :arguments [["cot" "hot" "bat" "fat"]]}
   {:expected false :arguments [["to" "top" "stop" "tops" "toss"]]}
   {:expected true :arguments [["spout" "do" "pot" "pout" "spot" "dot"]]}
   {:expected true :arguments [["share" "hares" "shares" "hare" "are"]]}
   {:expected false :arguments [["share" "hares" "hare" "are"]]}])

(defn- levenshtein-distance [seq1 seq2]
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

(defn solution [words]
  (letfn [(distances [word]
            (count (filter #(= 1 (levenshtein-distance word %))
                           words)))]
    (let [memoized-distances (memoize distances)]
      (> 2 (count (filter #(>= 1 (memoized-distances %))
                          words))))))
