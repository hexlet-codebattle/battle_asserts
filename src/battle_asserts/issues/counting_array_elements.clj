(ns battle-asserts.issues.counting-array-elements
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Return a hash map that shows how many times each element occurs in the given array.")

(def signature
  {:input [{:argument-name "arr"
            :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "hash" :nested {:name "integer"}}}})

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
