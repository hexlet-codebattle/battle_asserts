(ns battle-asserts.issues.odd-all-way
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given an array of numbers, return an array which contains all the odd numbers in the orginal array,
                  which also have odd indexes. If there is no such numbers, return `[0]`.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -9 9) 15 25)))

(def test-data
  [{:expected [0] :arguments [[1 2 3]]}
   {:expected [3 5] :arguments [[1 3 3 5 5]]}
   {:expected [3 11 15] :arguments [[1 3 1 6 9 11 13 15]]}
   {:expected [1] :arguments [[0 1 2 2 4]]}])

(defn solution [numbers]
  (let [result (reduce (fn [acc elem]
                         (let [index (first elem)
                               number (second elem)]
                           (if (and (odd? index) (odd? number))
                             (conj acc number)
                             acc)))
                       []
                       (map-indexed vector numbers))]
    (if (empty? result)
      [0]
      result)))
