(ns battle-asserts.issues.ruby-squish
  (:require [clojure.string :as string]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Returns the string, first removing all whitespace on both ends
                 of the string, and then changing remaining consecutive whitespace
                 groups into one space each. Note that it handles both ASCII and
                 Unicode whitespace.")

(defn separators []
  (string/join (repeat (rand-int 10) (rand-nth ["\t" "\n" " "]))))

(defn arguments-generator []
  (gen/tuple
    (gen/elements
      (repeatedly
        20
        #(string/join
          (separators)
          (conj
            (into
              [(separators)]
              (string/split
                (faker/sentence {:words-range [1,5] :lang :ru})
                #"\s"))
            " "))))))

(def test-data
  [{:expected "Multi-line string is so cool"
    :arguments ["  Multi-line \n       string    \t   is   \n   so cool   \n"]}])

(defn squeeze_spaces [str]
  (string/replace str #"\s+" " "))

(defn solution [str]
 (-> str string/triml string/trimr squeeze_spaces))
