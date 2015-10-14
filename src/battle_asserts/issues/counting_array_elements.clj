(ns battle-asserts.issues.counting-array-elements
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function, which return hash with count of occurence of each elements of given array")

(defn arguments-generator []
  (let [words (faker/words {:lang :en :n 15})]
    (gen/tuple (gen/vector (gen/elements words)))))

(defn solution [arr]
  (reduce #(update-in %1 [%2] (fnil inc 0)) {} arr))
