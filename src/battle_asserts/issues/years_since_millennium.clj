(ns battle-asserts.issues.years-since-millennium
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Count how many years have passed since nearest millenium.")

(defn arguments-generator
  []
  (gen/tuple (gen/resize 10000 gen/pos-int)))

; (gen/sample (arguments-generator) 1)

(defn solution
  [x]
  (mod x 1000))
