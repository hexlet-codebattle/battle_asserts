(ns battle-asserts.issues.clock-angle
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Clock angle problem.
                 What is the angle between the hour and minute hand at a given time.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 11) (gen/choose 0 59)))

(defn solution [h m]
  (letfn [(hours-angle [h m]
            (+ (* h 30)
               (* m 0.5)))
          (minutes-angle [m]
                         (* m 6))]
    (Math/abs (- (hours-angle h m)
                 (minutes-angle m)))))
