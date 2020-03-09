(ns battle-asserts.issues.read-roman-numerals
  (:require [clojure.pprint :as pprint]
            [clojure.string]
            [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "Parse a Roman-numeral string and return the number it represents.")

(def signature
  {:input  [{:argument-name "num" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(to-roman [number]
            (pprint/cl-format nil "~@R" number))]
    (gen/tuple (gen/bind (gen/choose 1 3500) #(gen/return (to-roman %))))))

(def test-data
  [{:expected 14
    :arguments ["XIV"]}
   {:expected 827
    :arguments ["DCCCXXVII"]}
   {:expected 3999
    :arguments ["MMMCMXCIX"]}
   {:expected 48
    :arguments ["XLVIII"]}])

(defn solution [roman]
  (let [numerals {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000}]
    (loop [roman-reverse (clojure.string/reverse roman) last-element 0 sum 0]
      (if (empty? roman-reverse)
        sum
        (let [value (numerals (first roman-reverse))
              real-value (if (> last-element value) (* -1 value) value)]
          (recur (subs roman-reverse 1) value (+ sum real-value)))))))
