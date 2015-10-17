(ns battle-asserts.issues.flatten
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "ha ha")

(defn arguments-generator
  []
  (let [nested (gen/list (gen/one-of [gen/int (gen/list gen/int)]))]
    (gen/tuple (gen/resize 5 (gen/list (gen/one-of [gen/int nested]))))))

; (gen/sample (arguments-generator) 1)

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn solution
  [x]
  (filter (complement sequential?)
          (rest (tree-seq sequential? seq x))))
