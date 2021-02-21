(ns battle-asserts.issues.reverse-and-drop
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def description
  {:en "You are given a string of words. Drop each word with odd length and reverse each word with even one."
   :ru "Создайте функцию, которая удаляет слова с нечетной длиной в строке, а слова с четной длиной переворачивает."})

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn sent-generator []
  (let [len (gen/generate (gen/choose 7 15))]
    (letfn [(sentences [] (s/join " " (faker/words {:n len})))]
      (vec (repeatedly 20 sentences)))))

(defn arguments-generator []
  (gen/tuple (gen/elements (sent-generator))))

(def test-data
  [{:expected "eb"
    :arguments ["learn clojure be happy"]}
   {:expected "emos tset htiw htgnel"
    :arguments ["some test words with odd length"]}])

(defn solution [sentence]
  (s/join #" " (reduce (fn [acc word]
                         (if-not (odd? (count word))
                           (conj acc (s/reverse word))
                           acc)) [] (s/split sentence #" "))))
