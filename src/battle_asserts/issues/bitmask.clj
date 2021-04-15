(ns battle-asserts.issues.bitmask
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :easy)

(def tags ["bits-operation" "strings"])

(def description
  {:en "Create a function that applies `bitmask` to `bitstring`. Use logical operator `AND`."
   :ru "Создайте функцию, которая применяет `bitmask` к `bitstring`. Используйте логический оператор `И`."})

(def signature
  {:input [{:argument-name "bitstring" :type {:name "string"}}
           {:argument-name "bitmask" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn bitstrings-generator []
  (let [length (gen/generate (gen/choose 8 15))]
    (letfn [(bit-gen [] (gen/generate (gen/choose 0 1)))
            (bitstring [] (s/join "" (repeatedly length bit-gen)))]
      (vec (repeatedly 20 bitstring)))))

(defn arguments-generator []
  (let [bitstrings (bitstrings-generator)]
    (gen/tuple (gen/elements bitstrings)
               (gen/elements bitstrings))))

(def test-data
  [{:expected "101101" :arguments ["101101" "101101"]}
   {:expected "0011" :arguments ["1011" "0111"]}
   {:expected "100" :arguments ["101" "110"]}])

(defn solution [bitstring bitmask]
  (let [bits (mapv vector (s/split bitstring #"") (s/split bitmask #""))
        result (reduce
                (fn [acc bit-pair]
                  (if (and (= (first bit-pair) "1") (= (last bit-pair) "1"))
                    (conj acc "1")
                    (conj acc "0"))) [] bits)]
    (s/join #"" result)))
