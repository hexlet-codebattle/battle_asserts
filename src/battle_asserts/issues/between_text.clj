(ns battle-asserts.issues.between-text
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :easy)

(def tags ["training" "strings"])

(def disabled true)

(def description
  {:en "Write function that returns subtext from text between given patterns."
   :ru "Напишите функцию, которая возвращает подтекст из текста между заданным шаблоном."})

(def signature
  {:input [{:argument-name "text" :type {:name "string"}}
           {:argument-name "left" :type {:name "string"}}
           {:argument-name "right" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [variants (repeatedly 2 #(faker/sentence {:words-range [4 4]}))
        left-variants (mapv #(rand-nth (first (partition 2 (s/split % #" ")))) variants)
        right-variants (mapv #(rand-nth (last (partition 2 (s/split % #" ")))) variants)]
    (gen/tuple (gen/elements variants) (gen/elements left-variants) (gen/elements right-variants))))

(def test-data
  [{:expected "foo" :arguments ["<a>foo</a>" "<a>" "</a>"]}
   {:expected "text" :arguments ["this is 'text'" "'" "'"]}
   {:expected "oni" :arguments ["xonix" "x" "x"]}
   {:expected "google" :arguments ["www.google.com" "www." ".com"]}
   {:expected " wow " :arguments ["oh wow such example" "oh" "such"]}])

(defn solution [string left right]
  (let [result (-> (str left "(.*)" right)
                   re-pattern
                   (re-find string)
                   rest)]
    (if (empty? result)
      ""
      (first result))))
