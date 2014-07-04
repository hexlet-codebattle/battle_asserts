(ns fixtures.array_fetch)

(defn get-value-by-index
  [arr index default-value]
  (if (and (>= index 0) (< index (count arr)))
     (arr index)
     default-value))

(defn fetch
  [arr index default-value]
  (if (> index 0)
    (get-value-by-index arr index default-value)
    (get-value-by-index arr (+ (count arr) index) default-value)))

(defn check []
  (let [arr [\a \b \c]]
    (assert (= \b (fetch arr 1 \d)))
    (assert (= \d (fetch arr 5 \d)))
    (assert (= \c (fetch arr -1 \d)))
    (assert (= \d (fetch arr -5 \d)))))
