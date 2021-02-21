(ns battle-asserts.issues.replace-and-uppercase
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description
  {:en "You are given a string of words. You need to find replace `_` symbols with spaces and capitalize every word in string."
   :ru "Создайте функцию, которая заменяет символ `_` пробелами и переводит каждое слово в строке в верхний регистр."})

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
  [{:expected "Learn Clojure Be Happy"
    :arguments ["learn_clojure_be_happy"]}
   {:expected "Learn Ruby Win Tournaments"
    :arguments ["learn_ruby_win_tournaments"]}])

(defn solution [sentence]
  (s/join #" " (map s/capitalize (s/split sentence #"_"))))
