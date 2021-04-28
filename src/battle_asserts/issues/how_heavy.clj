(ns battle-asserts.issues.how-heavy
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["math" "physics"])

(def description
  {:en "Given `radius` and `height` (in cm), calculate the mass of a cylinder when it's filled with water and the cylinder itself doesn't weigh anything. Use ceil rounding for answer."
   :ru "Дан `radius` и `height` (в см), рассчитайте массу цилиндра, заполненного полностью водой, массу самого цилиндра не учитывается. Используйте оккругление вверх."})

(def signature
  {:input [{:argument-name "radius" :type {:name "integer"}}
           {:argument-name "height" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 20) (gen/choose 1 100)))

(def test-data
  [{:expected 1 :arguments [4 10]}
   {:expected 8 :arguments [15 10]}
   {:expected 170 :arguments [30 60]}])

(defn solution [radius height]
  (int (Math/ceil (* Math/PI (Math/pow radius 2) height 0.001))))
