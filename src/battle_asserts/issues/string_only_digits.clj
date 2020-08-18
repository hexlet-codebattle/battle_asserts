(ns battle-asserts.issues.string-only-digits
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Check whether the given string contains only digit characters in.")

(def signature
  {:input  [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [words (repeatedly 10 faker/word)]
    (gen/tuple (gen/elements words))))

(def test-data
  [{:expected false
    :arguments ["some test"]}
   {:expected true
    :arguments ["1231012"]}
   {:expected false
    :arguments ["12hey3112"]}
   {:expected false
    :arguments ["1231!!!!!"]}])

(defn solution [str]
  (not (nil? (re-matches #"[0-9]+" str))))
