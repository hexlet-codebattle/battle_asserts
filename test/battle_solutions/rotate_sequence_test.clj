(ns battle-solutions.rotate-sequence-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn rotate-sequence
  [shift seq]
  (let [one-lap-shift (rem shift
                           (count seq))
        border (if (< 0 one-lap-shift)
                 one-lap-shift
                 (- (count seq)
                    (Math/abs one-lap-shift)))]
    (vec (concat
          (subvec (vec seq) border)
          (subvec (vec seq) 0 border)))))

(deftest test-asserts
  (assert-equal [3 4 5 1 2] (rotate-sequence 2 [1 2 3 4 5]))
  (assert-equal [4 5 1 2 3] (rotate-sequence -2 [1 2 3 4 5]))
  (assert-equal [2 3 4 5 1] (rotate-sequence 6 [1 2 3 4 5]))
  (assert-equal  '(:b :c :a) (rotate-sequence 1 '(:a :b :c)))
  (assert-equal  '(:c :a :b) (rotate-sequence -4 '(:a :b :c))))
