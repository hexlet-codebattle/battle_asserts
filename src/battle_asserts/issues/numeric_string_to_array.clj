(ns battle-asserts.issues.numeric-string-to-array
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [battle-asserts.utility :as utility]))

(def level :elementary)

(def description "Convert a numeric string to array of numbers.
                  It is granted that numbers in array are non-negative.")

(def signature
  {:input [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (letfn [(input-string []
            (s/join (repeatedly (gen/generate (gen/choose 2 10)) #(rand-int 10))))]
    (gen/tuple (gen/elements (repeatedly 50 input-string)))))

(def test-data
  [{:expected [1 2 3]
    :arguments ["123"]}
   {:expected [3 0 9 0 2]
    :arguments ["30902"]}
   {:expected [1 0 1 0 0]
    :arguments ["10100"]}])

(defn solution [str]
  (if (s/blank? str)
    []
    (vec (map #(Integer/parseInt %) (s/split str #"")))))

