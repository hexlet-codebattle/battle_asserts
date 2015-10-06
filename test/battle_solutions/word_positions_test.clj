(ns battle-solutions.word-positions-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn word-positions
  [string word]
  (letfn [(step [x]
            (when (. x find)
              (cons (. x start) (lazy-seq (step x)))))]
    (let [m (re-matcher (re-pattern (str "\\b" word "\\b"))
                        string)]
      (vec (step m)))))

(deftest test-asserts
  (assert-equal [0] (word-positions "test" "test"))
  (assert-equal [0 5 10] (word-positions "test test test" "test"))
  (assert-equal [12] (word-positions "find a word in some sentence" "in")))
