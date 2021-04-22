(ns battle-asserts.issues.pie-slice
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math"])

(def description "Create a function that determines whether or not it's possible to split a pie fairly given these three parameters:
                  Total number of slices.
                  Number of recipients.
                  How many slices each person gets.")

(def signature
  {:input [{:argument-name "total" :type {:name "integer"}}
           {:argument-name "people" :type {:name "integer"}}
           {:argument-name "each" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer
             gen/small-integer
             gen/small-integer))

(def test-data
  [{:expected true :arguments [15 5 3]}
   {:expected true :arguments [8 3 2]}
   {:expected false :arguments [10 4 4]}
   {:expected true :arguments [26 13 2]}])

(defn solution [total people each]
  (>= total (* people each)))
