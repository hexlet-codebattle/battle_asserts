(ns battle-asserts.issues.string-made-of
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def description "You are given a string, which contains entirely of decimal digits (0-9). Each digit is made of a certain number of dashes,
                 as on LCD-display of calculator. For instance 1 is made of 2 dashes, 8 is made of 7 dashes and so on.
                 You have to write a function that takes this string message and returns the count of dashes in the string message.")

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
