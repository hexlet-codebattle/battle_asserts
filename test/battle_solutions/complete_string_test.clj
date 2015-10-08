(ns battle-solutions.complete-string-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn complete-string [s]
  (= (-> s
      seq
      sort
      clojure.string/join
      )
     "abcdefghijklmnopqrstuvwxyz"))

(deftest test-asserts
  (assert-equal false (complete-string "wyyga"))
  (assert-equal true (complete-string "qwertyuioplkjhgfdsazxcvbnm"))
  (assert-equal false (complete-string "ejuxggfsts"))
  (assert-equal true (complete-string "qpwoeirutyalskdjfhgmznxbcv")))

