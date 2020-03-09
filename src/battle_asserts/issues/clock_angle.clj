(ns battle-asserts.issues.clock-angle
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Calculate the angle between the hour and the minute hand of a clock at a given time.")

(def signature
  {:input [{:argument-name "hour" :type {:name "integer"}}
           {:argument-name "minute" :type {:name "integer"}}]
   :output {:type {:name "float"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 11) (gen/choose 0 59)))

(def test-data
  [{:expected 7.5 :arguments [3 15]}
   {:expected 0.0 :arguments [0 0]}
   {:expected 82.5 :arguments [0 15]}
   {:expected 275.0 :arguments [0 50]}
   {:expected 157.5 :arguments [3 45]}])

(defn solution [h m]
  (letfn [(hours-angle [h m]
            (+ (* h 30)
               (* m 0.5)))
          (minutes-angle [m]
            (* m 6))]
    (Math/abs (- (hours-angle h m)
                 (minutes-angle m)))))
