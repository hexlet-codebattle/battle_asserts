(ns battle-asserts.issues.two-trains-meet
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Compute how long after their deparature two trains will meet.
                 Assume that the trains travel between two points, along a single
                 section of track, going in opposite directions. The function should
                 consume the trains' speeds and the starting distance between the trains.")

(defn arguments-generator []
  (gen/tuple (gen/such-that pos? gen/nat)
             (gen/such-that pos? gen/nat)
             gen/pos-int))

(def test-data
  [{:expected 1 :arguments [50 50 100]}
   {:expected 4/3 :arguments [25 50 100]}])

(defn solution
  [v-train1 v-train2 distance]
  (/ distance (+ v-train1
                 v-train2)))
