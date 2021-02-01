(ns battle-asserts.issues.atm-pin-code
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description
  {:en "ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits. Create a function that takes a string and check if the PIN is valid."
   :ru "Банкомат принимает 4-х значный или 6-значный ПИН код, который состоит только из цифр. Создайте функцию, которая проверяет корректность ПИН кода."})

(def signature
  {:input [{:argument-name "pin" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(four-digit-code [] (str (gen/generate (gen/choose 1000 9999))))
          (six-digit-code [] (str (gen/generate (gen/choose 100000 999999))))
          (invalid-code [] (gen/generate (gen/elements (faker/words {:lang :en :n 10}))))]
    (gen/tuple (gen/elements [(four-digit-code) (six-digit-code) (invalid-code)]))))

(def test-data
  [{:expected true :arguments ["1234"]}
   {:expected true :arguments ["123456"]}
   {:expected false :arguments ["12345"]}
   {:expected false :arguments ["some pin code"]}
   {:expected false :arguments ["m8te"]}])

(defn solution [code]
  (letfn [(only-digit? [pin] (empty? (re-matches #"^[0-9]+$" pin)))]
    (let [len (count code)]
      (and (not (only-digit? code)) (or (= len 4) (= len 6))))))
