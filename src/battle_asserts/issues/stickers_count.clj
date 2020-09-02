(ns battle-asserts.issues.stickers-count
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an `n * n * n` Rubik's cube, find the number of stickers that are needed to cover the whole cube.")

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer))

(def test-data
  [{:expected 6 :arguments [1]}
   {:expected 24 :arguments [2]}
   {:expected 54 :arguments [3]}])

(defn solution [num]
  (* 6 num num))
