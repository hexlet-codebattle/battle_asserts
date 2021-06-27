(ns battle-asserts.issues.reverse_everywhere
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Reverse each character of word in array and after that reverse array itself."
   :ru "Переверните каждое слово в массиве слов, а затем и сам массив."})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (let [words (faker/words {:lang :en :n 90})]
    (gen/tuple (gen/vector (gen/elements words) 2 8))))

(def test-data
  [{:expected ["emosewa" "si" "erujolc"]
    :arguments [["clojure" "is" "awesome"]]}
   {:expected ["yoj" "sgnirb" "ybur"]
    :arguments [["ruby" "brings" "joy"]]}
   {:expected ["atad" "elpmas" "emos"]
    :arguments [["some" "sample" "data"]]}])

(defn solution [words]
  (let [reversed-words (mapv s/reverse words)
        reversed-elements (rseq reversed-words)]
    (vec reversed-elements)))
