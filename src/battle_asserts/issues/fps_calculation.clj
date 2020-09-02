(ns battle-asserts.issues.fps-calculation
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that returns the number of frames shown in a given number of minutes for a certain FPS.
                  Note: FPS stands for \"frames per second\" and it's the number of frames a computer screen shows every second.")

(def signature
  {:input [{:argument-name "minutes" :type {:name "integer"}}
           {:argument-name "fps" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer
             gen/small-integer))

(def test-data
  [{:expected 60 :arguments [1 1]}
   {:expected 600 :arguments [10 1]}
   {:expected 15000 :arguments [10 25]}])

(defn solution [minutes fps]
  (* minutes fps 60))
