(ns battle-asserts.issues.jackpot
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Create a function that takes in an array (slot machine outcome)
                  and returns true if all elements in the array are identical, and false otherwise.
                  The array will contain 4 elements.")

(def signature
  {:input [{:argument-name "elements" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [element (gen/elements (faker/words {:lang :en :n 5}))]
    (gen/tuple (gen/tuple element element element element))))

(def test-data
  [{:expected true :arguments [["9919" "9919" "9919" "9919"]]}
   {:expected false :arguments [["abc" "abc" "abb" "abc"]]}
   {:expected true :arguments [["@" "@" "@" "@"]]}])

(defn solution [elements]
  (let [first-elem (first elements)
        filtered (filter #(= first-elem %) elements)]
    (= (count elements) (count filtered))))
