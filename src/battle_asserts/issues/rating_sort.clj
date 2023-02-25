(ns battle-asserts.issues.rating-sort
  (:require [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as util]))

(def level :easy)

(def disabled true)

(def tags ["strings" "collections" "hash-maps" "sorting"])

(def description
  {:en "Implement a function that find top 3 Codebattle players by rating and return list of their names. Note that rating is string!"
   :ru "Создайте функцию, которая находит топ 3 игроков Codebattle по рейтингу и возвращает список их имен. Примечание: рейтинг представлен строкой!"})

(def signature
  {:input [{:argument-name "players" :type {:name "array" :nested {:name "hash" :nested {:name "string"}}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [players (repeatedly 60 util/gen-name)
        ratings (map str (repeatedly 30 #(str (gen/generate (gen/choose 0 9999)))))
        stats (repeatedly 60 (fn [] {:player (rand-nth players) :rating (rand-nth ratings)}))]
    (gen/tuple (gen/vector (gen/elements stats) 4 15))))

(def test-data
  [{:expected ["Igor" "John" "Ann"]
    :arguments [[{:player "Ann" :rating "3"} {:player "John" :rating "5"} {:player "Igor" :rating "7"} {:player "Ivan" :rating "1"}]]}
   {:expected ["vtm" "Andrew" "Igor"]
    :arguments [[{:player "Ann" :rating "3"} {:player "John" :rating "5"} {:player "Igor" :rating "7"} {:player "Ivan" :rating "1"} {:player "vtm" :rating "9999"} {:player "Andrew" :rating "100"}]]}])

(defn solution [players]
  (->> players
       (map (fn [stats] (update stats :rating #(Integer/parseInt %))))
       (sort-by :rating >)
       (take 3)
       (mapv :player)))
