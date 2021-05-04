(ns battle-asserts.issues.get-alphabet
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Return an alphabet of a given string; the given string is represented as an array of characters.
        An alphabet of a string is the set of all distinct characters used in that string.
        The resulting string goes in order from `a` to `z`."
   :ru "Верните алфавит переданной строки; строка представляет собой массив символов.
        Алфавит строки - это набор все различных символов, присутствующих в строке.
        Символы результирующей строки расположены в порядке от `a` до `z`."})

(def signature
  {:input  [{:argument-name "s" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (let [sentences (repeatedly 50 faker/sentence)]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected "adfs"
    :arguments ["asfsfdss"]}
   {:expected "acgt"
    :arguments ["acgtgcgagtg"]}
   {:expected "1234"
    :arguments ["4123214"]}
   {:expected "+-.<>[]"
    :arguments ["+++[><<]<-."]}])

(defn solution [string]
  (-> string
      seq
      distinct
      sort
      s/join))
