(ns battle-asserts.issues.sort-by-binary
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Sort array of integer numbers by count of 1's in it's binary representation.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int 2 20)))

(def test-data
  [{:expected [1 2 4 3]
    :arguments [[1 2 3 4]]}
   {:expected [8 9 6 7]
    :arguments [[9 8 7 6]]}
   {:expected [64 5 7 255]
    :arguments [[255 7 5 64]]}
   {:expected [4 2 5 7]
    :arguments [[5 4 2 7]]}])

(defn binary-count [i]
  (->> i
       Integer/toBinaryString
       (filter #(= % \1))
       count))

(defn solution [arr]
  (sort-by binary-count arr))
