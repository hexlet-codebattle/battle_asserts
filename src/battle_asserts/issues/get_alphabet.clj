(ns battle-asserts.issues.get-alphabet
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Return an alphabet of a given string; the given string is represented as an array of characters. An alphabet of a strin is the set of all distinct characters used in that string.")

(defn signature []
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [sentences (repeatedly 50 faker/sentence)]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected "adfs"
    :arguments ["asfsfdss"]}
   {:expected "acgt"
    :arguments ["acgtgcgagtg"]}
   {:expected "1234"
    :arguments ["4123214"]}
   {:expected "+-.<>[]"
    :arguments ["+++[><<]<-."]}])

(defn solution [string]
  (-> string
      seq
      distinct
      sort
      clojure.string/join))
