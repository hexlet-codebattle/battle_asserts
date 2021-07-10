(ns battle-asserts.issues.sort-by-name
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.name :as f]))

(def level :elementary)

(def tags ["training" "strings" "collections"])

(def description
  {:en "Implement a function which sorts people names by their last name."
   :ru "Создайте функцию, которая сортирует комбинацию имени и фамилии людей по фамилии."})

(def signature
  {:input [{:argument-name "names" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn gen-name [] (str (f/first-name) " " (f/last-name)))

(defn arguments-generator []
  (let [names (repeatedly 60 gen-name)]
    (gen/tuple (gen/vector (gen/elements names) 3 15))))

(def test-data
  [{:expected ["Harold Abelson" "Rich Hickey" "Yukihiro Matsumoto" "Jose Valim"]
    :arguments [["Jose Valim" "Yukihiro Matsumoto" "Rich Hickey" "Harold Abelson"]]}
   {:expected ["Bill Gates" "Barbara Liskov" "Linus Torvalds"]
    :arguments [["Barbara Liskov" "Bill Gates" "Linus Torvalds"]]}])

(defn solution [names]
  (vec (sort-by #(last (s/split % #" ")) names)))
