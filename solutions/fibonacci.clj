(ns fibonacci)

(defn fibo-finder
  [x]
  (if  (= x 0)
    0
    (if  (= x 1)
      1
      (+  (fibo-finder  (dec x))  (fibo-finder  (- x 2))))))
