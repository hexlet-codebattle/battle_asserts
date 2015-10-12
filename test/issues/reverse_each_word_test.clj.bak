(ns battle-solutions.reverse-each-word-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn reverse-each-word
  [string]
  (-> string
      (clojure.string/split #" ")
      (->> (map clojure.string/reverse)
           (clojure.string/join " "))))

(deftest test-asserts
  (let [input "olleH ,ereht dna woh era ?uoy"
        output "Hello there, and how are you?"]
    (assert-equal output (reverse-each-word input)))
  (let [input "m’I ,eniF .sknahT dnA ?uoY"
        output "I’m Fine, Thanks. And You?"]
    (assert-equal output (reverse-each-word input)))
  (let [input "ehT kciuq nworb xof spmuj revo eht yzal god"
        output "The quick brown fox jumps over the lazy dog"]
    (assert-equal output (reverse-each-word input))))
