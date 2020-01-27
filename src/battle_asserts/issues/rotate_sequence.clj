(ns battle-asserts.issues.rotate-sequence
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Given an array and a number, generate an array with values shifted left or right by a given number.
                  The number could be positive or negative; positive number shifts the array forward, negative shifts it backwards.")

(def disabled true)

(defn arguments-generator []
  (gen/tuple gen/small-integer (gen/not-empty (gen/vector gen/small-integer))))

(def test-data
  [{:expected [4 5 1 2 3]
    :arguments [-2 [1 2 3 4 5]]}
   {:expected [3 4 5 1 2]
    :arguments [2 [1 2 3 4 5]]}
   {:expected [2 3 4 5 1]
    :arguments [6 [1 2 3 4 5]]}
   {:expected  '(:b :c :a)
    :arguments [1 '(:a :b :c)]}
   {:expected  '(:c :a :b)
    :arguments [-4 '(:a :b :c)]}])

(defn solution [shift seq]
  (let [one-lap-shift (rem shift
                           (count seq))
        border (if (pos? one-lap-shift)
                 one-lap-shift
                 (- (count seq)
                    (Math/abs one-lap-shift)))]
    (vec (concat
          (subvec (vec seq) border)
          (subvec (vec seq) 0 border)))))
