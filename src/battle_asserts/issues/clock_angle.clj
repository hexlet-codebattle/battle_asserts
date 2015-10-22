(ns battle-asserts.issues.clock-angle
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Calculate the angle between an hour and a minute hand at a given time.")

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
