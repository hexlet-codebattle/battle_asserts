(ns battle-asserts.issues.string-only-digits
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def description "Check whether the given string contains only digit characters in.")

(def signature
  {:input  [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [numbers (s/join "" (gen/sample (gen/choose 0 9)))
        words (s/join "" (repeatedly 3 faker/word))]
    (gen/tuple (gen/elements [numbers words]))))

(def test-data
  [{:expected false
    :arguments ["sometest"]}
   {:expected true
    :arguments ["1231012"]}
   {:expected true
    :arguments ["6001667522"]}
   {:expected false
    :arguments ["sensefruitquestion"]}])

(defn solution [str]
  (not (nil? (re-matches #"[0-9]+" str))))
