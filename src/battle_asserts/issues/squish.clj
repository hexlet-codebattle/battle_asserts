(ns battle-asserts.issues.squish
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "Given a text. Remove all the whitespaces from both ends of the text. Then collapse groups of whitespaces inside the text into single whitespace each. Handles both ASCII and Unicode whitespaces."
   :ru "Дан текст. Удалите все пробельные символы с обоих концов текста. Сверните группы пробельных символов внутри строки в один пробел. Обработайте как ASCII так и Unicode пробелы."})

(def signature
  {:input  [{:argument-name "text" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn- random-separator []
  (rand-nth ["\t" "\n" " "]))

(defn- random-separators [n]
  (s/join (repeatedly n random-separator)))

(defn- prepared-string []
  (->>
   (faker/words {:lang :en :n 10})
   (map #(str (random-separators 5) % (random-separators 5)))
   (s/join)))

(defn arguments-generator []
  (gen/tuple (gen/elements (repeatedly 20 prepared-string))))

(def test-data
  [{:expected "Multi-line string is so cool"
    :arguments ["  Multi-line \n       string    \t   is   \n   so cool   \n"]}])

(defn squeeze-spaces [sentence]
  (s/replace sentence #"\s+" " "))

(defn solution [sentence]
  (-> sentence
      s/triml
      s/trimr
      squeeze-spaces))
