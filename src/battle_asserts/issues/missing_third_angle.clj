(ns battle-asserts.issues.missing-third-angle
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "2 out of 3 angles are given in a triangle, in degrees.
                  Write a function that classifies the missing angle as \"acute\", \"right\", or \"obtuse\".
                  `An acute angle is less than 90 degrees.`
                  `A right angle is exactly 90 degrees.`
                  `An obtuse angle is greater than 90 degrees (but less than 180 degrees).`
                  The sum of angles of any triangle is always 180 degrees.")

(def signature
  {:input [{:argument-name "first" :type {:name "integer"}}
           {:argument-name "second" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator
  []
  (gen/tuple gen/small-integer gen/small-integer))

(def test-data
  [{:expected "obtuse" :arguments [27 59]}
   {:expected "acute" :arguments [135 11]}
   {:expected "right" :arguments [45 45]}])

(defn solution [first second]
  (let [third (- 180 first second)]
    (cond
      (> third 90) "obtuse"
      (< third 90) "acute"
      :else "right")))
