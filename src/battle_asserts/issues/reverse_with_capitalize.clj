(ns battle-asserts.issues.reverse-with-capitalize
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as fk]))

(def level :medium)

(def tags ["strings"])

(def description
  {:en "Create a function that takes a string as input and reverses the order of the letters in each word, preserving case and punctuation."
   :ru "Напишите функцию, которая принимает на вход строку и меняет порядок букв в каждом слове на обратный с сохранением регистра и пунктуации."})

(def signature
  {:input  [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- gen-capitalized-sentence []
  (let [sentence (fk/sentence)
        splitted (s/split sentence #" ")
        capitalized (map
                     (fn [el] (if (gen/generate gen/boolean) (s/capitalize el) el)) splitted)]
    (s/join #" " capitalized)))

(defn arguments-generator []
  (gen/tuple (gen/elements (repeatedly 40 gen-capitalized-sentence))))

(def test-data
  [{:expected "Тевирп! Онвад ен ьсиледив."
    :arguments ["Привет! Давно не виделись."]}
   {:expected "Olleh! Era uoy."
    :arguments ["Hello! Are you."]}
   {:expected "Dlrowolleh11!"
    :arguments ["11Helloworld!"]}
   {:expected "Noitcartsba si netfo eno roolf evoba uoy."
    :arguments ["Abstraction is often one floor above you."]}])

(defn- capitalized? [word]
  (not= word (s/lower-case word)))

(defn- has-sign? [word]
  (or (s/ends-with? word "?")
      (s/ends-with? word "!")
      (s/ends-with? word ".")))

(defn- reverse-string [string]
  (if (capitalized? string)
    (s/capitalize (s/reverse (s/lower-case string)))
    (s/reverse string)))

(defn- reverse-word [word]
  (if (has-sign? word)
    (let [sign (last word)
          without-sign (s/join #"" (drop-last word))]
      (str (reverse-string without-sign) sign))
    (reverse-string word)))

(defn solution [sentence]
  (->> (s/split sentence #" ")
       (map reverse-word)
       (s/join #" ")))

