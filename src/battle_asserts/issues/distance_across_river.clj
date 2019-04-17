(ns battle-asserts.issues.distance-across-river
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Compute the distance a boat travels across a river, given the width of the river, the boat's speed perpendicular to the river, and the river's speed.")

(defn signature []
  {:input [{:argument-name "width" :type {:name "float"}}
           {:argument-name "v_boat" :type {:name "float"}}
           {:argument-name "v_river" :type {:name "float"}}]
   :output {:type {:name "float"}}})

(defn pairs-gen
  [[x y]]
  (gen/fmap #(vector (* x %) (* y %))
            gen/s-pos-int))

(defn arguments-generator []
  (gen/fmap (fn [[[x y] factor]]
              [x (* x factor) (* y factor)])
            (gen/tuple (pairs-gen [9 12])
                       gen/s-pos-int)))

(def test-data
  [{:expected 5.0 :arguments [3 6 8]}
   {:expected 10.0 :arguments [10 10 0]}
   {:expected 5.0 :arguments [4 3 9/4]}])

(defn solution
  [width v-boat v-river]
  (let [offset (/ (* width v-river)
                  v-boat)]
    (Math/hypot width offset)))
