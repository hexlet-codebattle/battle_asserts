(ns battle-asserts.issues.reverse-even
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description
  {:en "You are given a string of words. Reverse each word with even length."
   :ru "Создайте функцию, которая переворачивает слова с четной длиной в строке."})

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn sent-generator []
  (let [len (gen/generate (gen/choose 3 15))]
    (letfn [(sentences [] (s/join "_" (faker/words {:n len})))]
      (vec (repeatedly 30 sentences)))))

(defn arguments-generator []
  (gen/tuple (gen/elements (sent-generator))))

(def test-data
  [{:expected "learn ybur eb happy"
    :arguments ["learn ruby be happy"]}
   {:expected "emos tset words htiw neve htgnel"
    :arguments ["some test words with even length"]}])

(defn solution [sentence]
  (s/join #" " (map (fn [word]
                      (if (even? (count word))
                        (s/reverse word)
                        word)) (s/split sentence #" "))))
