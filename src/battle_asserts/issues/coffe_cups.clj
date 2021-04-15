(ns battle-asserts.issues.coffe-cups
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["training"])

(def description "For each of 6 coffee cups you buy, you get a 7th cup free.
                  In total, you get 7 cups. Create a function that takes `n` cups bought and return
                  the total number of cups.")

(def signature
  {:input [{:argument-name "cups" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 5 1000)))

(def test-data
  [{:expected 14 :arguments [12]}
   {:expected 280 :arguments [240]}
   {:expected 969 :arguments [831]}])

(defn solution [cups]
  (+ cups (int (Math/floor (/ cups 6)))))
