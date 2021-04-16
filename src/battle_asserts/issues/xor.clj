(ns battle-asserts.issues.xor
  (:require [clojure.test.check.generators :as gen]
            [clojure.string]))

(def level :easy)

(def tags ["bits-operation" "strings"])

(def description
  {:en "Given two strings, return a string generated by XORing them. Of course, you XOR their bit representations."
   :ru "Примените битовую операцию \"исключающее ИЛИ\" для битового предсталения двух строк и возвратите полученную строку."})

(def signature
  {:input  [{:argument-name "str1" :type {:name "string"}}
            {:argument-name "str2" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple gen/string-ascii
             gen/string-ascii))

(def test-data
  [{:expected "5*"
    :arguments ["xor" "ME"]}
   {:expected "AAAA"
    :arguments ["jjjj" "++++"]}])

(defn solution
  [str1 str2]
  (clojure.string/join (map char
                            (map bit-xor
                                 (map int
                                      str1)
                                 (map int
                                      str2)))))
