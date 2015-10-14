(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of numbers, arrange them in a way that yields the largest value.")

(defn arguments-generator []
  (gen/tuple (gen/not-empty (gen/vector (gen/choose 0 547) 2 8))))

(defn my-comparator [val1 val2]
  (compare (Integer. (str val2 val1))
           (Integer. (str val1 val2))))

(defn solution [array]
  (BigInteger. (clojure.string/join (sort my-comparator array))))
