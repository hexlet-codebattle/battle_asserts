(ns battle-asserts.issues.power-digits-sum
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Create a method to calculate sum of digits for 2**n
                 For example, 2**15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.")

(defn arguments-generator []
  (gen/tuple (gen/choose 0 30)))

(defn solution [n]
  (reduce + (map #(Character/digit % 10)
                 (str (apply * (repeat n 2))))))
