(ns battle-solutions.clock-angle-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn clock-angle [h m]
  (letfn
   [(hours-angle [h m]
      (+ (* h 30)
         (* m 0.5)))
    (minutes-angle [m]
                   (* m 6))]
    (Math/abs (- (hours-angle h m)
                 (minutes-angle m)))))

(deftest test-asserts
  (assert-equal 0.0 (clock-angle 0 0))
  (assert-equal 7.5 (clock-angle 3 15))
  (assert-equal 82.5 (clock-angle 0 15))
  (assert-equal 275.0 (clock-angle 0 50))
  (assert-equal 157.5 (clock-angle 3 45)))
