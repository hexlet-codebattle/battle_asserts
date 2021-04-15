(ns battle-asserts.issues.find-nemo
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "You are given a string of words. You need to find the word `Nemo`, and return a string like this: \"I found Nemo at [the order of the word you find nemo]!\". If you can't find Nemo, return \"I can't find Nemo :(\"."
   :ru "Создайте функцию, которая ищет `Nemo` в строке и если он был найден, возвращает \"I found Nemo at [позиция Немо в строке]!\". Если Немо не был найден, то верните \"I can't find Nemo :(\"."})

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn sent-generator []
  (let [len (gen/generate (gen/choose 3 15))]
    (letfn [(nemo-sent [] (s/join " " (shuffle (conj (faker/words {:n len}) "Nemo"))))
            (sentences [] (s/join " " (faker/words {:n len})))]
      (vec (concat (repeatedly 15 nemo-sent) (repeatedly 15 sentences))))))

(defn arguments-generator []
  (gen/tuple (gen/elements (sent-generator))))

(def test-data
  [{:expected "I can't find Nemo :(" :arguments ["Hello world"]}
   {:expected "I found Nemo at 2!" :arguments ["Hi Nemo"]}
   {:expected "I found Nemo at 1!" :arguments ["Nemo James Nemo"]}
   {:expected "I found Nemo at 1!" :arguments ["Nemo is me"]}])

(defn solution [sentence]
  (let [words (s/split sentence #" ")
        index (.indexOf words "Nemo")]
    (if (neg? index)
      "I can't find Nemo :("
      (str "I found Nemo at " (inc index) "!"))))

