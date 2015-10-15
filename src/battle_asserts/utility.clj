(ns battle-asserts.utility)

(defn drop-nth [coll index]
  (keep-indexed #(if (not= index %1) %2) coll))
