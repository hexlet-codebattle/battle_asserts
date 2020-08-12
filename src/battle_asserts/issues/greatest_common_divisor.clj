(ns battle-asserts.issues.greatest-common-divisor
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Create function that calculates GCD (Greatest Common Divisor).")

(def signature
  {:input [{:argument-name "x" :type {:name "integer"}}
           {:argument-name "y" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 50) (gen/choose 1 50)))

(def test-data
  [{:arguments [8 24]
    :expected 8}
   {:arguments [8 26]
    :expected 2}
   {:arguments [42 56]
    :expected 14}
   {:arguments [15 50]
    :expected 5}])

(defn solution [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))
