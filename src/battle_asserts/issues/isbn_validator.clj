(ns battle-asserts.issues.isbn-validator
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Validate given ISBN identifier.
                 An ISBN is a ten digit code which identifies a book.
                 The first nine digits represent the book and the last digit is used to make sure the ISBN is correct
                 To verify an ISBN you obtain the sum of 10 times the first digit, 9 times the second digit, 8 times the third
                 digit ... all the way till you add 1 times the last.digit. if the sum leaves no remainder when divided by
                 11 the code is valid ISBN.")

(def signature
  {:input  [{:argument-name "code" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- special-sum [code]
  (->>
   (range (count code) 0 -1)
   (map * code)
   (apply +)))

(defn- valid-isbn-code []
  (let [nine-first-numers (vec (repeatedly 9 #(rand-int 10)))
        special-sum-without-last (special-sum (conj nine-first-numers 0))]
    (->>
     (mod special-sum-without-last 11)
     (- 11)
     (conj nine-first-numers)
     (apply format "%d-%d%d%d-%d%d%d%d%d-%d"))))

(defn- invalid-isbn-code []
  (apply format "%d-%d%d%d-%d%d%d%d%d-%d"
         (repeatedly 10 #(rand-int 10))))

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 valid-isbn-code))
                          (gen/elements (repeatedly 50 invalid-isbn-code))])))

(def test-data
  [{:expected true
    :arguments ["0-684-84328-5"]}
   {:expected true
    :arguments ["960-425-059-0"]}
   {:expected true
    :arguments ["85-359-0277-5"]}
   {:expected true
    :arguments ["0-85131-041-9"]}
   {:expected false
    :arguments ["0-85131-041-8"]}])

(defn- to-integer-array [code]
  (map read-string
       (re-seq #"\d" code)))

(defn solution [code]
  (->
   code
   to-integer-array
   special-sum
   (mod 11)
   zero?))
