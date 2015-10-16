(ns battle-asserts.issues.power-of-two
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an integer, write a function to determine if it is a power of two.")

(defn arguments-generator []
  (let [power-of-two [1 2 8 32 256 1024 4096 16348]]
    (gen/tuple (gen/one-of [(gen/choose 0 30000)
                            (gen/elements power-of-two)]))))

(defn solution [n]
  (and (not (zero? n))
       (zero? (bit-and n (dec n)))))
