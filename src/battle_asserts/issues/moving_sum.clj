(ns battle-asserts.issues.moving-sum
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Return the 'sliding sums' array of `numbers` with window size equal to `window`.
        For example, the sums of array `[1 2 3 4 5]` with window `2` will be `[(1 + 2) (2 + 3) (3 + 4) (4 + 5)] -> [3 5 7 9]`.
        If the size of the window is larger than the size of the array, return the array with the sum of all the numbers."
   :ru "Верните массив 'скользящих сумм' массива чисел `numbers` с размером окна равным `window`.
        Например, суммы массива `[1 2 3 4 5]` с окном `2` будут `[(1 + 2) (2 + 3) (3 + 4) (4 + 5)] -> [3 5 7 9]`.
        Если размер окна больше размера массива, то верните массив с суммой всех чисел"})

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "window" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -30 30) 5 12) (gen/choose 2 8)))

(def test-data
  [{:expected [3 5 7 9] :arguments [[1 2 3 4 5] 2]}
   {:expected [6 9 12] :arguments [[1 2 3 4 5] 3]}
   {:expected [10 14] :arguments [[1 2 3 4 5] 4]}
   {:expected [15] :arguments [[1 2 3 4 5] 6]}])

(defn solution [numbers window]
  (if (< (count numbers) window)
    (vector (reduce + numbers))
    (mapv #(reduce + %) (partition window 1 numbers))))
