(ns battle-asserts.issues.ascii-sum
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description
  {:en "Count sum of ASCII representation of characters in a given string."
   :ru "Рассчитайте сумму ASCII представления символов в строке."})

(def signature
  {:input [{:argument-name "word" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/elements (faker/words {:lang :en :n 50}))))

(def test-data
  [{:expected 448 :arguments ["test"]}
   {:expected 756 :arguments ["clojure"]}
   {:expected 864 :arguments ["frontend"]}])

(defn solution [word]
  (reduce + (map int word)))
