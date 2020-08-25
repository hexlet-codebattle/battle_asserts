(ns battle-asserts.issues.sam-with-frodo
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Sam and Frodo need to be close. If they are side by side in the array, your function should return true.
                  If there is other persons between them, return false.")

(def signature
  {:input [{:argument-name "persons" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [elements-count (gen/generate (gen/choose 2 6))]
    (gen/tuple (gen/shuffle (faker/words {:lang :en :n elements-count})))))

(def test-data
  [{:expected true
    :arguments [["Sam" "Frodo"]]}
   {:expected false
    :arguments [["Frodo" "Saruman" "Sam"]]}
   {:expected true
    :arguments [["Orc" "Sam" "Frodo" "Legolas"]]}])

(defn solution [persons]
  (let [sam-position (.indexOf  persons "Sam")
        frodo-position (.indexOf persons "Frodo")
        position-diff (Math/abs (- sam-position frodo-position))]
    (= position-diff 1)))
