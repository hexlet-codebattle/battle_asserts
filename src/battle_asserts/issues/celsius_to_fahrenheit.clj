(ns battle-asserts.issues.celsius-to-fahrenheit
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Сonvert Celsius temperature to Fahrenheit. Use floor rounding for the result temperature.")

(def signature
  {:input [{:argument-name "temperature" :type {:name "integer"}}]
   :output {:type {:name "result"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -500 500)))

(def test-data
  [{:expected 95 :arguments [35]}
   {:expected 10 :arguments [-12]}
   {:expected 32 :arguments [0]}
   {:expected -868 :arguments [-500]}
   {:expected 932 :arguments [500]}
   {:expected 451 :arguments [233]}])

(defn solution [temperature]
  (int (+ (/ (* temperature 9) 5) 32)))
