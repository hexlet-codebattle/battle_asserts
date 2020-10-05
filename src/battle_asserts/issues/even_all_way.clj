(ns battle-asserts.issues.even-all-way
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array of numbers, return an array which contains all the even numbers in the orginal array,
                  which also have even indexes.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -100 100) 5 15)))

(def test-data
  [{:expected [2] :arguments [[1 2 2 4 5]]}
   {:expected [2 4] :arguments [[1 3 2 6 4 8]]}
   {:expected [0 2 4] :arguments [[0 1 2 3 4]]}])

(defn solution [numbers]
  (reduce (fn [acc elem]
            (let [index (first elem)
                  number (second elem)]
              (if (and (even? index) (even? number))
                (conj acc number)
                acc)))
          []
          (map-indexed vector numbers)))
