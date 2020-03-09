(ns battle-asserts.issues.shorten-string
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Provide the word in the form of an acronym composed of the first letter,
                 the number of letters in the word minus 2 and the last letter of the word.")

(def signature
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [words (repeatedly 50 faker/word)]
    (gen/tuple (gen/such-that #(> (count %) 2) (gen/elements words)))))

(def test-data
  [{:expected "L10n"
    :arguments ["Localization"]}
   {:expected "M17n"
    :arguments ["Multilingualization"]}
   {:expected "I18n"
    :arguments ["Internationalization"]}])

(defn solution [s]
  (str (first s)
       (- (count s)
          2)
       (last s)))
