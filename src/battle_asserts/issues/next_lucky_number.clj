(ns battle-asserts.issues.next-lucky-number
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "Lucky numbers are defined as the numbers consisting only of digits 3 and 5.
                 So, given a number N, you have to return the least lucky number strictly greater than N.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 100)))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn prev-digits [prev-digits owerflow]
  (if (zero? owerflow)
    prev-digits
    (repeat (count prev-digits) 3)))

(defn solution [number]
  (Integer.
   (apply str
          (loop [n number, owerflow 0, res '()]
            (let [integer (quot n 10)
                  remnant (+ owerflow (rem n 10))
                  cur-digit (if (<= 4 remnant 5) 5 3)
                  cur-overflow (if (> remnant 5) 1 0)]
              (if (and (zero? integer) (zero? cur-overflow))
                (conj (prev-digits res owerflow) cur-digit)
                (recur integer cur-overflow (conj res cur-digit))))))))
