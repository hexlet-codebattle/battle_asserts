(ns battle-asserts.issues.bit-cycle-shift
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :easy)

(def tags ["bits operation" "strings"])

(def description
  {:en "Create a function that cyclical shifts bits of `bitstring` for `shift` amount."
   :ru "Создайте функцию, которая циклически сдвигает биты в `bitstring` на количество `shift`."})

(def signature
  {:input [{:argument-name "bitstring" :type {:name "string"}}
           {:argument-name "shift" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn bitstrings-generator []
  (let [length (gen/generate (gen/choose 8 15))]
    (letfn [(bit-gen [] (gen/generate (gen/choose 0 1)))
            (bitstring [] (s/join "" (repeatedly length bit-gen)))]
      (vec (repeatedly 30 bitstring)))))

(defn arguments-generator []
  (let [bitstrings (bitstrings-generator)]
    (gen/tuple (gen/elements bitstrings)
               (gen/one-of [(gen/choose 1 8) (gen/choose -8 -1)]))))
(gen/generate (arguments-generator))

(def test-data
  [{:expected "101111" :arguments ["111101" 3]}
   {:expected "1110" :arguments ["1011" -2]}
   {:expected "10100" :arguments ["10010" -3]}
   {:expected "10101" :arguments ["10101" 5]}])

(defn solution [bitstring shift]
  (let [length (count bitstring)]
    (if (= length (Math/abs shift))
      bitstring
      (if (pos? shift)
        (str (subs bitstring shift) (subs bitstring 0 shift))
        (str (subs bitstring (Math/abs shift) length) (subs bitstring 0 (Math/abs shift)))))))

