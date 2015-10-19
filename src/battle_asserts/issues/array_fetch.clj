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

(def test-data
  [{:expected \b :arguments [[\a \b \c] 1 \d]}
   {:expected \d :arguments [[\a \b \c] 5 \d]}
   {:expected \c :arguments [[\a \b \c] -1 \d]}
   {:expected \d :arguments [[\a \b \c] -5 \d]}])

; (gen/sample (arguments-generator) 5)

(defn solution
  [s index default]
  (let [positive-index (if (> index 0) index (+ (count s) index))]
    (nth s positive-index default)))
