(ns battle-asserts.issues.key-for-min-value
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write a method that accepts a hash map and returns the key of element with the minimum value.")

(defn arguments-generator []
  (letfn [(gen-word []
            (gen/elements (faker/words {:lang :en :n 50})))]
    (gen/tuple (gen/map (gen-word) gen/int))))

(def test-data
  [{:expected "j"
    :arguments [{"k" 2 "h" 3 "j" 1}]}
   {:expected "z"
    :arguments [{"o" 0 "z" -2 "j" 1}]}
   {:expected nil
    :arguments [{}]}])

(defn solution [hsh]
  (ffirst (sort-by last hsh)))
