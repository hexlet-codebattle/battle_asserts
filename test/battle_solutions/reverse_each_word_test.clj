(ns battle-solutions.reverse-each-word-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

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
    (assert-equal output (reverse-each-word input)))
  (let [input "World population increased from about 1.6 billion people in 1901 to 6.1 billion at the XXI century's end."
         output "dlroW noitalupop desaercni morf tuoba 6.1 noillib elpoep ni 1091 ot 1.6 noillib ta eht IXX s'yrutnec .dne"]
    (assert-equal output (reverse-each-word input))))
