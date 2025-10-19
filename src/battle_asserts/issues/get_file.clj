(ns battle-asserts.issues.get-file
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["training" "strings" "os"])

(def description
  {:en "Implement a function that returns the filename with extension from a given path."
   :ru "Создайте функцию, которая возвращает имя файла с расширением из переданного пути файловой системы."})

(def signature
  {:input [{:argument-name "filepath" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(words-count [] (gen/generate (gen/choose 2 8)))
          (extension [] (gen/generate (gen/elements [".txt" ".exe" ".mp3" "" ".docx" ".jpg" ".png" ".pdf"])))
          (filepath [] (repeatedly 15 #(str "C:/" (s/join "/" (faker/words {:lang :en :n (words-count)})) (extension))))]
    (gen/tuple (gen/elements (filepath)))))

(def test-data
  [{:expected "something.txt"
    :arguments ["C:/Projects/tests/texts/something.txt"]}
   {:expected "brain-games.exe"
    :arguments ["C:/brain-games.exe"]}
   {:expected "bar.sh"
    :arguments ["C:/Somedir/foo/bar.exe"]}
   {:expected "Beethoven_5.mp3"
    :arguments ["C:/Users/JohnDoe/Music/Beethoven_5.mp3"]}])

(defn solution [filepath]
  (let [dirs-and-file (s/split filepath #"/")]
    (last dirs-and-file)))
