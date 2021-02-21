(ns battle-asserts.issues.drop-odd
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description
  {:en "You are given a string of words. Drop each word with odd length."
   :ru "Создайте функцию, которая удаляет слова с нечетной длиной в строке."})

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
  [{:expected "be"
    :arguments ["learn clojure be happy"]}
   {:expected "some test with length"
    :arguments ["some test words with odd length"]}])

(defn solution [sentence]
  (s/join #" " (reduce (fn [acc word]
                         (if (not (odd? (count word)))
                           (conj acc word)
                           acc)) [] (s/split sentence #" "))))

