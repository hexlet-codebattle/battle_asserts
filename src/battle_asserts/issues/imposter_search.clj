(ns battle-asserts.issues.imposter-search
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def disabled true)

(def description "Create a function that calculates the chance of being an imposter.
                  The formula for the chances of being an imposter is `100 × (i / p)`
                  where `i` is the imposter count and `p` is the player count.
                  Make sure to round the value to the nearest integer and return the value as a percentage.")

(def signature
  {:input [{:argument-name "imposters" :type {:name "integer"}}
           {:argument-name "players" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 15) (gen/choose 2 20)))

(def test-data
  [{:expected "30%" :arguments [3 10]}
   {:expected "80%" :arguments [4 5]}
   {:expected "15%" :arguments [3 20]}])

(defn solution [imposters players]
  (let [percent (int (Math/floor (* 100 (/ imposters players))))]
    (str percent "%")))
