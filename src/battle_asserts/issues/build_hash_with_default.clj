(ns battle-asserts.issues.build-hash-with-default
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write function, which initialized hashmap with given array of keys and default value")

(defn arguments-generator []
  (let [word-generator (gen/elements (faker/words {:lang :en :n 30}))]
    (gen/tuple (gen/vector word-generator) gen/int)))

(def test-data
  [{:expected {:draft 0 :completed 0}
    :arguments [[:draft :completed] 0]}
   {:expected {:one 4 :two 4}
    :arguments [[:one :two] 4]}])

(defn solution [v default]
  (reduce #(assoc %1 %2 default) {} v))

