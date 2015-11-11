(ns battle-asserts.issues.mangling-sentence
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :easy)

(def description "#FIXME Напишите функцию сортирующую буквы в каждом слове предложения.")

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
  (gen/tuple (gen/elements (repeatedly 50 input-sentence))))

(def test-data
  [{:expected "Eey fo Entw, adn Eot fo Fgor, Loow fo Abt, adn Egnotu fo Dgo."
    :arguments ["Eye of Newt, and Toe of Frog, Wool of Bat, and Tongue of Dog."]}
   {:expected  "Hist aceeghlln denos't eems os adhr."
    :arguments ["This challenge doesn't seem so hard."]}
   {:expected "Eehrt aer emor ghinst beeentw aeehnv adn aehrt, Ahioort, ahnt aer ademrt fo in oruy hhilooppsy."
    :arguments ["There are more things between heaven and earth, Horatio, than are dreamt of in your philosophy."]}])

(defn solution [sentence]
  (->>
   sentence
   (re-seq #"(\w+)|([^\w])")
   (map first)
   (map s/lower-case)
   (map #(if (re-matches #"\w+" %) (sort %) %))
   flatten
   (map #(if (Character/isUpperCase %1) (s/upper-case %2) %2) sentence)
   s/join))
