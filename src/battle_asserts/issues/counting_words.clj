(ns battle-asserts.issues.counting-words
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as str]))

(def level :easy)

(def disabled true)

(def description "Return a vector with subvectors that shows how many times each word occurs in the given string.")

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "string"}}}}})

(defn arguments-generator []
  (let [strs (gen/vector (gen/elements (faker/words {:lang :en :n 15})) 3 10)
        sentence (gen/fmap #(str/join " " %) strs)]
    (gen/tuple sentence)))

(def test-data
  [{:expected [["make" 1] ["codebattle" 1] ["great" 1] ["again" 1]]
    :arguments ["make codebattle great again"]}
   {:expected [["codebattle" 2] ["is" 2] ["cool" 1] ["fun" 1] ["let" 2] ["us" 2] ["code" 1] ["battle" 1]]
    :arguments ["codebattle is cool, codebattle is fun, let us code, let us battle"]}])

(defn solution [sentence]
  (let [freq (->> sentence
                  (re-seq #"\w+")
                  frequencies)]
    (mapv (fn [elem] [(first elem) (last elem)]) freq)))
