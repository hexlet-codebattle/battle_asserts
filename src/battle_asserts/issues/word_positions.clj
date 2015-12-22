(ns battle-asserts.issues.word-positions
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a sentence and a word, find all the positions in which the word occurs in the sentence. Return an array of such positions.")

(defn arguments-generator []
  (letfn [(input []
            (let [words (faker/words {:lang :en :n 10})
                  word (rand-nth words)]
              (->> (cycle [word])
                   (take (rand-int 4))
                   (concat words)
                   shuffle
                   (s/join " ")
                   (vector word)
                   reverse)))]
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
