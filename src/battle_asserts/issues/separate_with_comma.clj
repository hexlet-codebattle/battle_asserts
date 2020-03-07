(ns battle-asserts.issues.separate-with-comma
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given a number as input, return a string with that number formatted with commas to separate each three digits from the right to make it look like a standard North American number.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 2000000)))

(def test-data
  [{:expected "1,238,592"
    :arguments [1238592]}
   {:expected "1"
    :arguments [1]}
   {:expected "10"
    :arguments [10]}
   {:expected "150"
    :arguments [150]}
   {:expected "1,234"
    :arguments [1234]}
   {:expected "15,075"
    :arguments [15075]}
   {:expected "123,456"
    :arguments [123456]}])

(defn solution [number]
  (->
   number
   str
   s/reverse
   (s/replace #"(\d\d\d)(?=\d)" "$1,")
   s/reverse))
