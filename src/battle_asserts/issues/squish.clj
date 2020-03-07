(ns battle-asserts.issues.squish
  (:require [clojure.string :as string]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Given a string. Remove all the whitespaces from both ends of the string. Then collapse groups of whitespaces inside the string into single whitespace each. Handles both ASCII and Unicode whitespaces.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- random-separator []
  (rand-nth ["\t" "\n" " "]))

(defn- random-separators [n]
  (string/join (repeatedly n random-separator)))

(defn- prepared-string []
  (->>
   (faker/words {:lang :en :n 10})
   (map #(str (random-separators 5) % (random-separators 5)))
   (string/join)))

(defn arguments-generator []
  (gen/tuple (gen/elements (repeatedly 20 prepared-string))))

(def test-data
  [{:expected "Multi-line string is so cool"
    :arguments ["  Multi-line \n       string    \t   is   \n   so cool   \n"]}])

(defn squeeze-spaces [sentence]
  (string/replace sentence #"\s+" " "))

(defn solution [sentence]
  (-> sentence
      string/triml
      string/trimr
      squeeze-spaces))
