(ns battle-asserts.issues.prime-number-count
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Create a function that finds how many prime numbers are in a sequence from zero to a given integer, inclusive.")

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 100)))

(def test-data
  [{:arguments [0] :expected 0}
   {:arguments [10] :expected 4}
   {:arguments [25] :expected 9}
   {:arguments [100] :expected 25}])

(defn prime? [n]
  (let [divisors (range 2 (inc (int (Math/sqrt n))))
        remainders (map #(mod n %) divisors)]
    (not-any? zero? remainders)))

(defn solution [n]
  (count (filter prime? (range 2 n))))
