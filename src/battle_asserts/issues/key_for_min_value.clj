(ns battle-asserts.issues.key-for-min-value
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given a hash map, return the keys of the elements with the smallest value.
                  The result should be sorted alphabetically.")

(def signature
  {:input  [{:argument-name "h" :type {:name "hash" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(gen-word []
            (gen/elements (faker/words {:lang :en :n 10})))]
    (gen/tuple (gen/map (gen-word) (gen/choose -10 10) {:min-elements 0 :max-elements 8}))))

(def test-data
  [{:expected ["damage" "detail"]
    :arguments [{"father" 2 "detail" -7 "education" 7 "morning" 3 "damage" -7 "powder" 5}]}
   {:expected ["j"]
    :arguments [{"k" 2 "h" 3 "j" 1}]}
   {:expected ["z"]
    :arguments [{"o" 0 "z" -2 "j" 1}]}
   {:expected []
    :arguments [{}]}])

(defn solution [hsh]
  (if (empty? hsh)
    []
    (let [minimum (apply min (vals hsh))]
      (vec (sort (keys (filter #(= minimum (val %)) hsh)))))))
