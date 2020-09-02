(ns battle-asserts.issues.equality-count
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Create a function that takes array of three integers and returns the amount
                  of integers which are of equal value.
                  Note: Function must return 0, 2 or 3.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator
  []
  (gen/tuple (gen/vector gen/small-integer 3)))

(def test-data
  [{:expected 2 :arguments [[17 17 3]]}
   {:expected 0 :arguments [[1 2 3]]}
   {:expected 3 :arguments [[33 33 33]]}])

(defn solution [numbers]
  (let [equal-size (count (set numbers))]
    (if (= equal-size 3)
      0
      (- 4 equal-size))))
