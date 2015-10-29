(ns battle-asserts.utility
  (:require [faker.generate :as faker]))

(defn drop-nth [coll index]
  (keep-indexed #(if (not= index %1) %2) coll))

(defn unique-words [amount]
  (letfn [(gen-words []
            (faker/words {:lang :en :n amount}))]
    (loop [words (gen-words)]
      (if (= (count words) (count (distinct words)))
        words
        (recur (gen-words))))))
