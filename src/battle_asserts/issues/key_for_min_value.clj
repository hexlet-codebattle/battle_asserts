(ns battle-asserts.issues.key-for-min-value
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a hash map, return the keys of the elements with the smallest value.
                 #FIXME Результат должен быть отсортирован в алфавитном порядке")

(defn arguments-generator []
  (letfn [(gen-word []
            (gen/elements (faker/words {:lang :en :n 50})))]
    (gen/tuple (gen/map (gen-word) gen/int))))

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
