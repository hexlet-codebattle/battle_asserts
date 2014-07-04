(ns array-fetch.multicode)

(defn fetch
  [s index default]
  (let [positive-index (if (> index 0) index (+ (count s) index))]
    (nth s positive-index default)))

(defn check []
  (let [arr [\a \b \c]]
    (assert (= \b (fetch arr 1 \d)))
    (assert (= \d (fetch arr 5 \d)))
    (assert (= \c (fetch arr -1 \d)))
    (assert (= \d (fetch arr -5 \d)))))
