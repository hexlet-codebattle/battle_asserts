(ns battle-asserts.issues.perfect-numbers
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :medium)

(def description "A number is \"perfect\" if the sum of its divisors equal the number itself.")

(defn arguments-generator []
  (let [perfect-numbers [6 28 496 8128]]
    (gen/tuple (gen/one-of [(gen/choose 0 1000)
                            (gen/elements perfect-numbers)]))))

(defn solution [num]
  (letfn [(divisors [num]
            (filter #(= (rem num %) 0)
                    (range 1 (inc (/ num 2)))))]
    (if (<= num 1)
      false
      (= num (reduce + (divisors num))))))
