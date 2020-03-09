(ns battle-asserts.issues.string-made-of
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given a string, which contains only decimal digits (0-9). Each digit is made of a certain number of dashes,
                 as on LCD-display of a calculator. For instance 1 is made of 2 dashes, 8 is made of 7 dashes and so on.
                 Return the total number of dashes in the input string.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(input-string []
            (s/join (repeatedly (rand-int 15) #(rand-int 10))))]
    (gen/tuple (gen/elements (repeatedly 50 input-string)))))

(def test-data
  [{:expected 18
    :arguments ["12134"]}
   {:expected 14
    :arguments ["452"]}
   {:expected 32
    :arguments ["9817567"]}
   {:expected 22
    :arguments ["76814"]}])

(defn solution [text]
  (let [m {:1 2 :2 5 :3 5 :4 4 :5 5 :6 6 :7 3 :8 7 :9 6 :0 6}]
    (->> text
         seq
         (map #(m (keyword (str %))))
         (reduce +))))
