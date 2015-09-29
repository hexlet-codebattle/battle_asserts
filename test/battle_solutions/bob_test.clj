(ns battle-solutions.bob-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]
            [clojure.string :as string]))

(defn bob-answer
  [sentence]
  (cond
    (and (= (string/upper-case sentence) sentence)
         (re-find #"[A-Z]" sentence)) "Whoa, chill out!"
    (= (last sentence) \?) "Sure."
    (string/blank? sentence) "Fine. Be that way!"
    :else "Whatever."))

(deftest test-asserts
  (assert-equal "Whoa, chill out!" (bob-answer "1, 2, 3 GO!"))
  (assert-equal "Whoa, chill out!" (bob-answer "ZOMBIES ARE COMING!!11!!1!"))
  (assert-equal "Whoa, chill out!" (bob-answer "WHAT THE HELL WERE YOU THINKING?"))
  (assert-equal "Whatever." (bob-answer "It is OK."))
  (assert-equal "Whatever." (bob-answer "Ending with ? means a question."))
  (assert-equal "Fine. Be that way!" (bob-answer ""))
  (assert-equal "Fine. Be that way!" (bob-answer "    "))
  (assert-equal "Sure." (bob-answer "4?")))
