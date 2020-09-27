(ns battle-asserts.issues.spelling-out
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Create a function which takes in a word and spells it out, by consecutively adding letters until the full word is completed.")

(def signature
  {:input [{:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (gen/tuple (gen/elements (faker/words {:lang :en :n 50}))))

(def test-data
  [{:expected ["b" "be" "bee"]
    :arguments ["bee"]}
   {:expected ["h" "ha" "hap" "happ" "happy"]
    :arguments ["happy"]}
   {:expected ["c" "cl" "clo" "cloj" "cloju" "clojur" "clojure"]
    :arguments ["clojure"]}])

(defn solution [word]
  (reduce (fn [acc ch]
            (conj acc (str (last acc) ch)))
          [] word))
