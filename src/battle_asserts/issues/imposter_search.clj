(ns battle-asserts.issues.imposter-search
  (:require [clojure.test.check.generators :as gen]
            [faker.name :as nm]))

(def level :elementary)

(def description "Create a function that find imposters in array of players, each player has its own role.
If no imposters were found, return `[\"there are no imposters here!\"]`.
If in array presented only imposters, return `[\"imposters are everywhere!\"]`.
In other cases return array of imposters names.")

(def signature
  {:input [{:argument-name "players" :type {:name "array" :nested {:name "array" :nested {:name "string"}}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(player-gen [] [(nm/first-name) (gen/generate (gen/elements ["imposter" "civilian"]))])
          (players-gen [] (repeatedly 5 player-gen))]
    (gen/tuple (gen/vector (gen/elements (players-gen)) 2 10))))

(def test-data
  [{:expected ["Vasya" "Jack"] :arguments [[["Daniel" "civilian"] ["Vasya" "imposter"] ["Jack" "imposter"]]]}
   {:expected ["there are no imposters here!"] :arguments [[["Harry" "civilian"]]]}
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

