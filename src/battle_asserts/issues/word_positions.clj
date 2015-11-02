(ns battle-asserts.issues.word-positions
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a text file and a word, find all the positions in which the word occurs in the file.
                 We’ll be asked to find the positions of many words in the same file.")

(defn arguments-generator []
  (letfn [(input []
            (let [sentence (faker/sentence {:words-range [1, 10]})
                  words (re-seq #"\w+" sentence)]
              [sentence, (rand-nth words)]))]
    (gen/elements (repeatedly 50 input))))

(def test-data
  [{:expected [12]
    :arguments ["find a word in some sentence" "in"]}
   {:expected [0]
    :arguments ["test" "test"]}
   {:expected [0 5 10]
    :arguments ["test test test" "test"]}])

(defn solution [string word]
  (letfn [(step [x]
            (when (.find x)
              (cons (.start x) (lazy-seq (step x)))))]
    (let [m (re-matcher (re-pattern (str "\\b" word "\\b"))
                        string)]
      (vec (step m)))))
