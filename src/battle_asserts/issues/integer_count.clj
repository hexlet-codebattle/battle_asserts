(ns battle-asserts.issues.integer-count
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def description
  {:en "Create a function that counts the integer's number of digits."
   :ru "Создайте функцию, которая подсчитывает количество цифр в числе."})

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -1000000 1000000)))

(def test-data
  [{:expected 2
    :arguments [45]}
   {:expected 3
    :arguments [-123]}
   {:expected 5
    :arguments [-12345]}
   {:expected 6
    :arguments [976543]}])

(defn solution [number]
  (let [numbers (s/split (str number) #"")
        sign (= (first numbers) "-")
        numbers-count (count numbers)]
    (if sign
      (dec numbers-count)
      numbers-count)))
