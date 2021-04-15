(ns battle-asserts.issues.power-calculator
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["physics" "training"])

(def description "Create a function that takes `voltage` and `current` and returns the power.")

(def signature
  {:input [{:argument-name "voltage" :type {:name "integer"}}
           {:argument-name "current" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -20 20) (gen/choose -20 20)))

(def test-data
  [{:expected 20 :arguments [2 10]}
   {:expected -3 :arguments [3 -1]}
   {:expected 0 :arguments [5 0]}])

(defn solution [voltage current] (* voltage current))
