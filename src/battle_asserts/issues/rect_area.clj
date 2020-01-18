(ns battle-asserts.issues.rect-area
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Calculate the area of a rectangle given its length and width")

(defn signature []
  {:input  [{:argument-name "width" :type {:name "integer"}}
            {:argument-name "height" :type {:name "integer"}}]
   :output {:type {:name "float"}}})

(defn arguments-generator []
  (gen/tuple gen/nat gen/nat))

(def test-data
  [{:expected 0 :arguments [0 0]}
   {:expected 2 :arguments [1 2]}
   {:expected 9 :arguments [3 3]}
   {:expected 4096 :arguments [64 64]}
   {:expected 13.5 :arguments [1.5 9]}])

(defn solution
  [width height]
  (* width height))
