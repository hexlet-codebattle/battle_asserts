(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of numbers, arrange them in a way that yields the largest value.")

(defn arguments-generator []
  (gen/tuple (gen/not-empty (gen/vector (gen/choose 0 547) 2 8))))

(def test-data
   [{:arguments [["meat" "mat" "team" "mate" "eat"]]
     :expected [["mate" "meat" "team"]]}
    {:arguments [["veer" "lake" "item" "kale" "mite" "ever"]]
     :expected [["ever" "veer"] ["item" "mite"] ["kale" "lake"]]}])

(defn my-comparator [val1 val2]
  (compare (Integer. (str val2 val1))
           (Integer. (str val1 val2))))

(defn solution [array]
  (BigInteger. (clojure.string/join (sort my-comparator array))))
