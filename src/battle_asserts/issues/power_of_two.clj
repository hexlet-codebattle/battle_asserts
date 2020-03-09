(ns battle-asserts.issues.power-of-two
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Determine if a given integer is a power of two.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [power-of-two [1 2 8 32 256 1024 4096 16348]]
    (gen/tuple (gen/one-of [(gen/choose 0 30000)
                            (gen/elements power-of-two)]))))

(def test-data
  [{:expected true
    :arguments [16]}
   {:expected false
    :arguments [20]}
   {:expected true
    :arguments [1]}
   {:expected false
    :arguments [258]}
   {:expected true
    :arguments [512]}
   {:expected false
    :arguments [513]}
   {:expected true
    :arguments [1024]}
   {:expected false
    :arguments [0]}])

(defn solution [n]
  (and (not (zero? n))
       (zero? (bit-and n (dec n)))))
