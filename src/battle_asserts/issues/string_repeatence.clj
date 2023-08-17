(ns battle-asserts.issues.string-repeatence
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["training" "strings"])

(def description
  {:en "Create a function that takes a string `sentence` and a number `n` and returns the repeated `sentence` an `n` number of times."
   :ru "Создайте функцию, которая принимает строку `sentence` и число `n` и возвращает строку `sentence` повторенное `n` раз."})

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}
           {:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/elements (faker/sentences {:n 30})) (gen/choose 0 10)))

(def test-data
  [{:expected "" :arguments ["Some sentence" 0]}
   {:expected "Clojure and RubyClojure and RubyClojure and Ruby" :arguments ["Clojure and Ruby" 3]}
   {:expected "Elixir" :arguments ["Elixir" 1]}
   {:expected "" :arguments ["Completly new sentence" -23]}])

(defn solution [sentence n]
  (if (zero? n)
    ""
    (letfn [(gen [] sentence)]
      (s/join "" (repeatedly n gen)))))
