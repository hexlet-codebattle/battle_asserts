(ns battle-asserts.issues.plural-or-singular
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Create a function that takes a word and
                  determines whether or not it is plural.
                  This is an oversimplification of the English language.
                  Ignore edge cases like \"goose\" and \"geese\", \"fungus\" and \"fungi\", etc.")

(def signature
  {:input  [{:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/elements (gen/shuffle (faker/words {:lang :en :n 5})))))

(def test-data
  [{:expected false
    :arguments ["fork"]}
   {:expected true
    :arguments ["forks"]}
   {:expected false
    :arguments ["clojure"]}
   {:expected true
    :arguments ["bytes"]}])

(defn solution [word]
  (= (last word) \s))
