(ns battle-asserts.issues.largest-pair-sum-in-array
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of integers, find the largest pair sum in it. For example,
                 the largest pair sum in [ 12, 34, 10, 6, 40 ] is 74.")

(defn arguments-generator []
  (gen/tuple (gen/vector gen/int)))

(defn solution [array]
  (apply + (take-last 2 (sort array))))
