(ns battle-asserts.issues.check-phone-number
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :medium)

(def tags ["strings"])

(def description
  {:en "Write a function to validate some strings that could potentially represent phone numbers."
   :ru "Напишите функцию, которая валидирует строку и проверяет, может ли она быть телефонным номером."})

(def signature
  {:input [{:argument-name "candidate" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- gen-phone-part []
  (let [numbers (s/join #"" (gen/sample (gen/choose 1 9) (gen/generate (gen/choose 2 3))))
        updated (rand-nth [numbers (s/join #"" [numbers (gen/generate gen/char-ascii)])])
        final (rand-nth [updated (str "(" updated ")")])]
    [numbers updated final]))

(defn- gen-phone-number []
  (let [first-part (rand-nth (gen-phone-part))
        second-part (rand-nth (gen-phone-part))
        third-part (rand-nth (gen-phone-part))
        combined-parts [first-part second-part third-part]
        result (rand-nth [(s/join #" " combined-parts)
                          (s/join #"" combined-parts)
                          (s/join #"-" combined-parts)])]
    result))

(defn arguments-generator []
  (let [numbers (repeatedly 40 gen-phone-number)]
    (gen/tuple (gen/elements numbers))))

(def test-data
  [{:expected true :arguments ["5555555555"]}
   {:expected true :arguments ["555555555"]}
   {:expected true :arguments ["555-5555"]}
   {:expected true :arguments ["(555) 555-5555"]}
   {:expected true :arguments ["(555) 555-555"]}
   {:expected true :arguments ["(555) 555-555-5555"]}
   {:expected false :arguments ["(555) 555a-555-5555"]}
   {:expected false :arguments ["555*-555-5555"]}
   {:expected false :arguments ["55a-555-5555"]}
   {:expected true :arguments ["55-55-55"]}
   {:expected true :arguments ["55 55 55"]}])

(defn solution [candidate]
  (not (nil?
        (re-matches #"^((8|0|((\+|00)\d{1,2}))[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{6,12}$"
                         candidate))))

