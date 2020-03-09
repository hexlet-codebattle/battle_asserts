(ns battle-asserts.issues.string-format
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Given a number as input, return a string \"Value is X\", 
                  where X is the given number with zeros added to the beginning so that there are 5 digits in total.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/choose 0 9)
                          (gen/choose 10 99)
                          (gen/choose 100 999)
                          (gen/choose 1000 9999)
                          (gen/choose 10000 99999)])))

(def test-data
  [{:expected "Value is 00123"
    :arguments [123]}
   {:expected "Value is 00005"
    :arguments [5]}
   {:expected "Value is 12345"
    :arguments [12345]}])

(defn solution [num]
  (format "Value is %05d" num))
