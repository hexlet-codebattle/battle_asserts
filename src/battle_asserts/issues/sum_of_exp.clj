(ns battle-asserts.issues.sum-of-exp
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Given an integer x, return the sum x + xx + xxx (x times) as a string. For example, for 2: 2 + 22 = 24.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 25)))

(def test-data
  [{:expected "369"
    :arguments [3]}
   {:expected "61725"
    :arguments [5]}
   {:expected "740736"
    :arguments [6]}
   {:expected "98765424"
    :arguments [8]}])

(defn sum-strings [string1 strign2]
  (loop [summand1 (map str (reverse string1))
         summand2 (map str (reverse strign2))
         overflow 0
         result ""]
    (if (and (empty? summand1) (empty? summand2) (zero? overflow))
      result
      (let [number1 (read-string (or (first summand1) "0"))
            number2 (read-string (or (first summand2) "0"))
            sum (+ number1 number2 overflow)]
        (recur (rest summand1)
               (rest summand2)
               (quot sum 10)
               (str (rem sum 10)
                    result))))))

(defn solution [number]
  (->>
   (range 1 (inc number))
   (map #(s/join (repeat % number)))
   (reduce sum-strings)))
