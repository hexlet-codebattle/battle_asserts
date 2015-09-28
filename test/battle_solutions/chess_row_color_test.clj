(ns battle-solutions.chess-row-color-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-black-row [letter num]
  (let [letter-nums (zipmap (seq "ABCDEFGH")
                            (range 1 9))]
    (even? (+ num
              (letter-nums letter)))))

(deftest test-asserts
  (assert-true (is-black-row \A 1))
  (assert-true (is-black-row \H 8))
  (assert-true (is-black-row \D 4))
  (assert-true (is-black-row \G 7))
  (assert-true (not (is-black-row \A 8)))
  (assert-true (not (is-black-row \H 1)))
  (assert-true (not (is-black-row \E 8)))
  (assert-true (not (is-black-row \E 4))))
