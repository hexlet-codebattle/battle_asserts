(ns battle-asserts.issues.secret-society
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "A group of friends have decided to start a secret society.
                  The name will be the first letter of each of their names.
                  Create a function that takes an array of names and returns the name of the secret society sorted in alphabetical order.")

(def signature
  {:input [{:argument-name "names" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(words [] (gen/elements (faker/words {:lang :en :n 50})))]
    (gen/tuple (gen/vector (words) 2 15))))

(def test-data
  [{:arguments [["Malcolm" "Adam" "Sarah"]]
    :expected  "AMS"}
   {:arguments [["Harry" "Ross" "Chandler" "Joey" "Rachel"]]
    :expected  "CHJRR"}])

(defn solution [names]
  (let [sorted-names (sort names)]
    (reduce (fn [acc name] (str
                            acc
                            (s/upper-case (first name))))
            "" sorted-names)))
