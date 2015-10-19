(ns battle-asserts.issues.counting-array-elements
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Write function, which return hash with count of occurence of each elements of given array")

(defn arguments-generator []
  (let [words (faker/words {:lang :en :n 15})]
    (gen/tuple (gen/vector (gen/elements words)))))

(def test-data
  [{:expected {:cat 1, :dog 1, :fish 2}
    :arguments [[:cat, :dog, :fish, :fish]]}
   {:expected {:bacon 1, :egg 1, :Spam 4}
    :arguments [[:Spam, :egg, :Spam, :Spam, :bacon, :Spam]]}])

(defn solution [arr]
  (reduce #(update-in %1 [%2] (fnil inc 0)) {} arr))
