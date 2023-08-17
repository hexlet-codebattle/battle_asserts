(ns battle-asserts.issues.imposter-search
  (:require [clojure.test.check.generators :as gen]
            [faker.name :as fk]))

(def level :easy)

(def tags ["collections" "strings"])

(def description
  {:en "Create a function that find imposters in array of players, each player has its own role.
        If no imposters were found, return `[\"there are no imposters here!\"]`.
        If in array presented only imposters, return `[\"imposters are everywhere!\"]`.
        In other cases return array of imposters names."
   :ru "Создайте функцию, которая находит самозванцев (imposter) в массиве игроков, у каждого игрока своя роль.
        Если самозванцев нет, верните `[\"there are no imposters here!\"]`.
        Если в массиве только самозванцы, верните `[\"imposters are everywhere!\"]`.
        В ином случае верните массив с именами самозванцев."})

(def signature
  {:input [{:argument-name "players" :type {:name "array" :nested {:name "array" :nested {:name "string"}}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(player-gen [] [(fk/first-name) (gen/generate (gen/elements ["imposter" "crewmate" "doctor" "officer" "ghost"]))])
          (players-gen [] (repeatedly 5 player-gen))]
    (gen/tuple (gen/vector (gen/elements (players-gen)) 2 10))))

(def test-data
  [{:expected ["Vasya" "Jack"] :arguments [[["Daniel" "crewmate"] ["Vasya" "imposter"] ["Jack" "imposter"]]]}
   {:expected ["there are no imposters here!"] :arguments [[["Harry" "crewmate"]]]}
   {:expected ["imposters are everywhere!"] :arguments [[["Jack" "imposter"]]]}])

(defn solution [players]
  (let [players-count (count players)
        imposters (reduce (fn [acc [name role]]
                            (if (= role "imposter")
                              (conj acc name)
                              acc))
                          []
                          players)
        imposters-count (count imposters)]
    (cond
      (= players-count imposters-count) ["imposters are everywhere!"]
      (zero? imposters-count) ["there are no imposters here!"]
      :else imposters)))
