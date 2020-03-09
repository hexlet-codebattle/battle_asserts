(ns battle-asserts.issues.build-hash-with-default
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Return a new hash map with elements of the given array as keys, and the given default value as values for those keys.")

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "hash" :nested {:name "integer"}}}})

(defn arguments-generator []
  (let [word-generator (gen/elements (faker/words {:lang :en :n 30}))]
    (gen/tuple (gen/vector word-generator) gen/small-integer)))

(def test-data
  [{:expected {:draft 0 :completed 0}
    :arguments [[:draft :completed] 0]}
   {:expected {:one 4 :two 4}
    :arguments [[:one :two] 4]}])

(defn solution [v default]
  (reduce #(assoc %1 %2 default) {} v))
