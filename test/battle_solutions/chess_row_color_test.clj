(ns battle-solutions.chess-row-color-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert]]))

(defn is-black-row? [letter num]
  (let [letter-nums (zipmap (seq "ABCDEFGH")
                            (range 1 9))]
    (even? (+ num
              (letter-nums letter)))))

(deftest test-asserts
  (assert (is-black-row? \A 1))
  (assert (is-black-row? \H 8))
  (assert (is-black-row? \D 4))
  (assert (is-black-row? \G 7))
  (assert (not (is-black-row? \A 8)))
  (assert (not (is-black-row? \H 1)))
  (assert (not (is-black-row? \E 8)))
  (assert (not (is-black-row? \E 4))))
