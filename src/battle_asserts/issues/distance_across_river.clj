(ns battle-asserts.issues.distance-across-river
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Compute the distance a boat travels across a river, given the width of the river, the boat's speed perpendicular to the river, and the river's speed.")

(defn pairs-gen
  [base-pair]
  (gen/fmap (fn [x] (map (fn [y] (* y x))
                         base-pair))
            (gen/such-that pos? gen/pos-int)))

(defn arguments-generator []
  (gen/fmap (fn [x]
              (let [pair (first x)
                    factor (second x)]
                (vector (first pair)
                        (* (first pair) factor)
                        (* (second pair) factor))))
            (gen/tuple (pairs-gen [9 12])
                       (gen/such-that pos? gen/pos-int))))

(def test-data
  [{:expected 5.0 :arguments [3 6 8]}
   {:expected 10.0 :arguments [10 10 0]}
   {:expected 5.0 :arguments [4 3 9/4]}])

(defn solution
  [width v-boat v-river]
  (let [offset (/ (* width v-river)
                  v-boat)]
    (Math/sqrt (+ (Math/pow width 2)
                  (Math/pow offset 2)))))
