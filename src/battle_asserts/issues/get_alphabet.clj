(ns battle-asserts.issues.get-alphabet
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write function that returns alphabet of current string as array of characters.")

(defn arguments-generator []
  (let [sentences (repeatedly 50 faker/sentence)]
    (gen/tuple (gen/elements sentences))))

(defn solution [string]
  (-> string
      seq
      distinct
      sort
      clojure.string/join))
