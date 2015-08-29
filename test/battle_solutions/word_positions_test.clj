(ns battle-solutions.word-positions-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn word-positions
  [string word]
  (letfn [(step [x]
            (when (. x find)
              (cons (. x start) (lazy-seq (step x)))))]
    (let [m (re-matcher (re-pattern (str "\\b" word "\\b")) 
                        string)]
      (vec (map #(if (> % 0) (- % 1) %)  
                (step m))))))

(deftest test-asserts
  (assert-equal [0] (word-positions "test" "test"))
  (assert-equal [0 4 9] (word-positions "test test test" "test"))
  (assert-equal [11] (word-positions "find a word in some sentence" "in")))
