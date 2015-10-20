(ns battle-asserts.issues.get-alphabet
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write a function that returns an alphabet of a given string; the given string is represented as an array of characters.")

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
