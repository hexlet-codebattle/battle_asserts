(ns battle-asserts.issues.char-replacement
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings" "collections"])

(def description
  {:en "Write a function that replaces every `character` for `replacement` from every string in array."
   :ru "Создайте функцию, которая заменяет каждый `character` на `replacement` для каждой строки в массиве."})

(def signature
  {:input [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "character" :type {:name "string"}}
           {:argument-name "replacement" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn arguments-generator []
  (letfn [(gen-char [] (str (gen/generate gen/char-alpha)))]
    (gen/tuple
     (gen/vector (gen/elements (faker/words {:n 20})) 3 10)
     (gen/elements (repeatedly 20 gen-char))
     (gen/elements (repeatedly 20 gen-char)))))

(def test-data
  [{:arguments [["test" "string" "example"] "t" "d"]
    :expected  ["desd" "sdring" "example"]}
   {:arguments [["learn" "clojure" "be" "happy"] "l" "L"]
    :expected  ["Learn" "cLojure" "be" "happy"]}
   {:arguments [["foo" "bar" "frontend"] "z" "Y"]
    :expected  ["foo" "bar" "frontend"]}])

(defn solution [words character replacement]
  (mapv #(s/replace % character replacement) words))
