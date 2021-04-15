(ns battle-asserts.issues.parity-bit
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :easy)

(def tags ["bits-operation" "strings"])

(def description
  {:en "Create a function that adds the correct parity bit to a binary string. Parity bits are used as a very simple checksum to ensure that binary data isn't corrupted during transit. Here's how they work:
* If a binary string has an odd number of `1's`, the parity bit is a `1`.
* If a binary string has an even number of `1's`, the parity bit is a `0`.
* The parity bit is appended to the end of the binary string."
   :ru "Создайте функцию, которая добавляет бит паритета в бинарную строку. Бит паритета используется для проверки чек-суммы, чтобы убедиться, что бинарные данные не были испорчены во время передачи. Алгоритм вычисления паритета:
* Если в бинарной строке нечетное количество единиц, то бит паритета равен единице.
* Если в бинарной строке четное количество единиц, то бит паритета равен нулю.
* Бит паритета добавляется в конец бинарной строки."})

(def signature
  {:input [{:argument-name "bitstring" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn bitstrings-generator []
  (let [length (gen/generate (gen/choose 3 15))]
    (letfn [(bit-gen [] (gen/generate (gen/choose 0 1)))
            (bitstring [] (s/join "" (repeatedly length bit-gen)))]
      (vec (repeatedly 30 bitstring)))))

(defn arguments-generator []
  (gen/tuple (gen/elements (bitstrings-generator))))

(def test-data
  [{:expected "1011010" :arguments ["101101"]}
   {:expected "10110111" :arguments ["1011011"]}
   {:expected "11111111" :arguments ["1111111"]}
   {:expected "1010" :arguments ["101"]}])

(defn solution [bitstring]
  (let [bits (s/split bitstring #"")
        ones-count (reduce
                    (fn [acc bit]
                      (if (= bit "1")
                        (inc acc)
                        acc)) 0 bits)]
    (if (odd? ones-count)
      (str bitstring "1")
      (str bitstring "0"))))
