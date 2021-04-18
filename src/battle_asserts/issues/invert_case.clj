(ns battle-asserts.issues.invert-case
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Implement a function that changes the case of each letter in the string to the opposite."
   :ru "Создайте функцию, которая меняет регистр каждого символа в строке на противоположный."})

(def signature
  {:input  [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- input-sentence []
  (let [sentence (faker/sentence {:lang :en :words-range [1 10]})
        modifiers [s/capitalize identity]
        punctuations [" " ", " "-"]]
    (->>
     sentence
     (re-seq #"(\w+)|([^\w])")
     (map first)
     (map #(if (re-matches #"(\w+)|(\.)" %) ((rand-nth modifiers) %) (rand-nth punctuations)))
     s/join)))

(defn arguments-generator []
  (gen/tuple (gen/elements (repeatedly 20 input-sentence))))

(def test-data
  [{:expected "hELLO, wORLD."
    :arguments ["Hello, World."]}
   {:expected "i lOVE clojure!"
    :arguments ["I Love CLOJURE!"]}
   {:expected "mIxEd CaSe"
    :arguments ["MiXeD cAsE"]}
   {:expected "FRONTEND? what IS it?"
    :arguments ["frontend? WHAT is IT?"]}])

(defn solution [str]
  (letfn [(inverted-case-char [char]
            (if (Character/isUpperCase char)
              (s/lower-case char)
              (s/upper-case char)))]
    (s/join (map inverted-case-char (seq str)))))
