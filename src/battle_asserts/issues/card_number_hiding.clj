(ns battle-asserts.issues.card-number-hiding
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Write a function that takes a credit card number and only displays the last four characters. The rest of the card number must be replaced by *."
   :ru "Создайте функцию, которая отображает последние 4 символа номера кредитной карты. Остальные символы замените на `*`."})

(def signature
  {:input  [{:argument-name "number" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(card-numbers []
            (s/join (repeatedly (gen/generate (gen/choose 8 15)) #(rand-int 10))))]
    (gen/tuple (gen/elements (repeatedly 20 card-numbers)))))

(def test-data
  [{:expected "******3456"
    :arguments ["1234123456"]}
   {:expected "*************2121"
    :arguments ["12341234561212121"]}
   {:expected "2134"
    :arguments ["2134"]}
   {:expected "*6814"
    :arguments ["76814"]}])

(defn solution [card-number]
  (let [length (count card-number)
        last-digits-pos (- length 4)
        last-digits (subs card-number last-digits-pos length)]
    (str (s/join "" (repeat last-digits-pos "*")) last-digits)))
