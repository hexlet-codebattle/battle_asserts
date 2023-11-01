(ns battle-asserts.issues.string-only-digits
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Check whether the given string contains only digit characters in."
   :ru "Проверьте, все ли символы в строке являются цифрами."})

(def signature
  {:input  [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- gen-words-or-numbers [n]
  (repeatedly n (fn []
                  (if (zero? (rand-int 2))
                    (gen/generate (gen/choose 0 9))
                    (faker/word)))))

(defn arguments-generator []
  (let [numbers (repeatedly 10 (fn [] (s/join "" (gen/sample (gen/choose 0 9)))))
        words (repeatedly 10 (fn [] (s/join "" (repeatedly 4 faker/word))))
        mixed (repeatedly 30 (fn [] (s/join "" (gen-words-or-numbers 4))))]
    (gen/tuple (gen/elements (concat numbers words mixed)))))

(def test-data
  [{:expected false
    :arguments ["sometest"]}
   {:expected true
    :arguments ["1231012"]}
   {:expected false
    :arguments ["test1012"]}
   {:expected true
    :arguments ["6001667522"]}
   {:expected false
    :arguments ["hello1world"]}
   {:expected false
    :arguments ["sensefruitquestion"]}])

(defn solution [str]
  (not (nil? (re-matches #"[0-9]+" str))))
