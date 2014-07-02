(ns array-transpose.multicode) 

(defn transpose
  [vectors]
  (if (not-empty vectors)
    (apply mapv vector vectors)
    []))

(defn check []
  (assert (= [[1 :a] [2 :b] [3 :c]] (transpose [[1 2 3] [:a :b :c]])))
  (assert (= [[1 3 5] [2 4 6]] (transpose [[1 2] [3 4] [5 6]])))
  (assert (= [] (transpose []))))

