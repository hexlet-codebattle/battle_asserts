(ns battle-asserts.issues.computers-count
  (:require [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as h]))

(def level :elementary)

(def tags ["training" "strings"])

(def description
  {:en "Write a method/function that takes a number (int) as input, and outputs a word in the plural or singular (only simple cases) corresponding to the specified number."
   :ru "Написать метод/функцию, который/которая на вход принимает число (int), а на выходе выдает слово в множественном или единственном числе (только простые случаи), соответствующем указанному количеству."})

(def signature
  {:input [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple h/gen-pos-num))

(def test-data
  [{:expected "1 computer" :arguments [1]}
   {:expected "512 computers" :arguments [512]}
   {:expected "1024 computers" :arguments [1024]}])

(defn solution [number]
  (if (= number 1)
    "1 computer"
    (str number " computers")))
