(ns battle-asserts.issues.array-fetch
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "ehu nana")

(defn arguments-generator
  []
  (gen/tuple (gen/list gen/int)
             gen/int
             gen/int))

; (gen/sample (arguments-generator) 5)

(defn solution
  [s index default]
  (let [positive-index (if (> index 0) index (+ (count s) index))]
    (nth s positive-index default)))
