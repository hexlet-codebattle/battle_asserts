(ns battle-asserts.issues.prime-array
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math" "collections"])

(def description
  {:en "Generate the array of first n prime numbers."
   :ru "Сгенерируйте массив первых n простых чисел."})

(def signature
  {:input [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 15)))

(def test-data
  [{:arguments [10] :expected [2 3 5 7 11 13 17 19 23 29]}
   {:arguments [4] :expected [2 3 5 7]}
   {:arguments [1] :expected [2]}])

(defn prime? [n]
  (let [divisors (range 2 (inc (int (Math/sqrt n))))
        remainders (map #(mod n %) divisors)]
    (not-any? zero? remainders)))

(defn solution [n]
  (vec (->> (iterate inc 2)
            (filter prime?)
            (take n))))
