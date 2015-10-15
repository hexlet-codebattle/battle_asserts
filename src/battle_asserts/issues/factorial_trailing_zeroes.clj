(ns battle-asserts.issues.factorial-trailing-zeroes
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function, which return the number of trailing zeroes in n!.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 90)))

(defn factorial [n]
  (apply *' (range 1 (+ n 1))))

(defn solution [n]
  (->>
   n
   factorial
   str
   (re-find #"0+$")
   count))
