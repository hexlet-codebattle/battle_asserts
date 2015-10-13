(ns battle-solutions.word-pattern-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn transform [array]
  (->>
   array
   (reduce-kv #(assoc %1 %3 (conj (get %1 %3) %2)) {})
   vals))

(defn word-pattern [pattern words]
  (=
   (transform (into [] (seq pattern)))
   (transform (clojure.string/split words #" "))))

(deftest test-asserts
  (assert-true (word-pattern "abba" "dog cat cat dog"))
  (assert-true (not (word-pattern "abba" "dog cat cat fisth")))
  (assert-true (word-pattern "sos" "bond james bond"))
  (assert-true (word-pattern "zzbinqs" "dragonborn dragonborn by his honor in sworm"))
  (assert-true (not (word-pattern "abba" "dog dog dog dog"))))
