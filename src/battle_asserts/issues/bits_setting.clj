(ns battle-asserts.issues.bits-setting
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :easy)

(def tags ["bits-operation" "strings"])

(def description
  {:en "Create a function that sets bits of `bitmask` to `bitstring`. Use logical operator `OR`. Size of `bitstring` and `bitmask` are the same."
   :ru "Создайте функцию, которая выставляет биты из `bitmask` в `bitstring`. Используйте логический оператор `ИЛИ`. Размеры строк всегда одинаковы."})

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
   {:expected "1111" :arguments ["1011" "0111"]}
   {:expected "111" :arguments ["101" "110"]}])

(defn solution [bitstring bitmask]
  (let [bits (mapv vector (s/split bitstring #"") (s/split bitmask #""))
        result (reduce
                (fn [acc bit-pair]
                  (if (and (= (first bit-pair) "0") (= (last bit-pair) "0"))
                    (conj acc "0")
                    (conj acc "1"))) [] bits)]
    (s/join #"" result)))
