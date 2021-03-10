(ns battle-asserts.issues.convert-and-reverse
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Reverse input number and convert it to string. Keep number sign.")

(def signature
  {:input  [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose -999999 999999)))

(def test-data
  [{:expected "3214321"
    :arguments [1234123]}
   {:expected "-54321"
    :arguments [-12345]}
   {:expected "0"
    :arguments [0]}])

(defn reverse-number
  ([number] (reverse-number number 0))
  ([number reversed]
   (if (zero? number)
     reversed
     (reverse-number (quot number 10)
                     (+ (rem number 10) (* reversed 10))))))

(defn solution [number]
  (str (reverse-number number)))
