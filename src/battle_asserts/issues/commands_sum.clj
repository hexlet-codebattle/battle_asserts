(ns battle-asserts.issues.commands-sum
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Several people are standing in a row and need to be divided into two teams
                  The first person goes into `team 1`, second goes to `team 2`, the third goes into `team 1`, the fourth into `team 2` and so on.
                  You are given an array of integers - the weights of the people.
                  Write a function that calculates total weights of both team and return an array in which first element is total weights sum of `team 1` and second element is total weights of `team 2`.")

(def signature
  {:input  [{:argument-name "players" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 50 90) 5 15)))

(def test-data
  [{:expected [180 190] :arguments [[50 60 60 45 70 85]]}
   {:expected [110 105] :arguments [[50 60 60 45]]}
   {:expected [50 60] :arguments [[50 60]]}])

(defn solution [players]
  (let [indexed (map-indexed list players)
        first-team (map #(last %) (filter #(zero? (rem (first %) 2)) indexed))
        second-team (map #(last %) (filter #(not= (rem (first %) 2) 0) indexed))]
    [(apply + first-team) (apply + second-team)]))
