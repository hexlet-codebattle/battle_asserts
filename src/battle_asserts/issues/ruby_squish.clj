(ns battle-asserts.issues.ruby-squish
  (:require [clojure.string :as string]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Returns the string, first removing all whitespace on both ends
                 of the string, and then changing remaining consecutive whitespace
                 groups into one space each. Note that it handles both ASCII and
                 Unicode whitespace.")

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
