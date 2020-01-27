(ns battle-asserts.issues.word-positions
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a sentence and a word, find all the positions in which the word occurs in the sentence. Return an array of such positions.")

(def signature
  {:input  [{:argument-name "sentence" :type {:name "string"}}
            {:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

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
