(ns battle-asserts.issues.count-words
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Given a string, return the number of words in it."
   :ru "Дана строка, верните количество слов в ней."})

(def signature
  {:input  [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [sentences (repeatedly 50 #(faker/sentence {:words-range [0 12]}))]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected 4 :arguments ["The quick brown fox"]}
   {:expected 7 :arguments ["The cat in the hat is back"]}
   {:expected 0 :arguments [""]}])

(defn solution [str]
  (if (s/blank? str)
    0
    (count (s/split str #"\s+"))))


