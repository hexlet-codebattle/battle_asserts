(ns battle-asserts.issues.next-lucky-number
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Lucky numbers are defined as the numbers consisting only of digits 3 and 5.
                 Write a function which, given a number N, returns the smallest lucky number strictly greater than N.")

(defn signature []
  {:input  [{:argument-name "number" :type {:name "integer"}}]
   :output {:type {:name "integer"}})

(defn arguments-generator []
  (gen/tuple (gen/choose 0 100)))

(def test-data
  [{:expected 5
    :arguments [4]}
   {:expected 53
    :arguments [45]}
   {:expected 53
    :arguments [48]}
   {:expected 333
    :arguments [130]}
   {:expected 333
    :arguments [98]}
   {:expected 3333
    :arguments [949]}
   {:expected 353
    :arguments [339]}])

(defn prev-digits [prev-digits owerflow]
  (if owerflow
    (repeat (count prev-digits) 3)
    prev-digits))

(defn solution [number]
  (Integer.
   (s/join
    (loop [n (inc number), owerflow 0, res '()]
      (let [integer (quot n 10)
            remnant (+ owerflow (rem n 10))
            cur-digit (if (<= 4 remnant 5) 5 3)
            cur-overflow (if (> remnant 5) 1 0)
            must-overwrite-prev (or (not (zero? owerflow)) (> cur-digit remnant))]
        (if (and (zero? integer) (zero? cur-overflow))
          (conj (prev-digits res must-overwrite-prev) cur-digit)
          (recur integer cur-overflow (conj (prev-digits res must-overwrite-prev) cur-digit))))))))
