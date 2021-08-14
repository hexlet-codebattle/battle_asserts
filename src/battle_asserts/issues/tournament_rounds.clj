(ns battle-asserts.issues.tournament-rounds
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def tags ["math" "training"])

(def description
  {:en "Codebattle programmers are rewriting tournaments in React. They need to calculate the `number of rounds' based on the `number of participants' in tournament. Create such function to help them."
   :ru "Программисты Codebattle переписывают турниры на React. Им нужно рассчитать `количество раундов`, исходя из `количества участников` в турнире. Помогите им создать такую функцию."})

(def signature
  {:input [{:argument-name "participants" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (let [samples (map #(int (Math/pow 2 %)) (range 1 15))]
    (gen/tuple (gen/elements samples))))

(def test-data
  [{:expected 1 :arguments [2]}
   {:expected 4 :arguments [16]}
   {:expected 5 :arguments [32]}
   {:expected 3 :arguments [8]}])

(defn- log2 [n]
  (/ (Math/log n) (Math/log 2)))

(defn solution [participants]
  (int (log2 participants)))
