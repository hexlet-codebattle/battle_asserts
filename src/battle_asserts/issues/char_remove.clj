(ns battle-asserts.issues.char-remove
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Write a function that removes every `character` from every string in array."
   :ru "Создайте функцию, которая принимает массив строк и символ, затем удаляет этот символ из каждой строки в массиве."})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "character" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(gen-char [] (str (gen/generate gen/char-alpha)))]
    (gen/tuple
     (gen/vector (gen/elements (faker/words {:n 20})) 3 10)
     (gen/elements (repeatedly 20 gen-char)))))

(def test-data
  [{:arguments [["test" "two" "example"] "t"]
    :expected  ["es" "wo" "example"]}
   {:arguments [["learn" "clojure" "be" "happy"] "l"]
    :expected  ["earn" "cojure" "be" "happy"]}
   {:arguments [["foo" "bar" "frontend"] "z"]
    :expected  ["foo" "bar" "frontend"]}])

(defn solution [words character]
  (mapv #(s/replace % character "") words))
