(ns battle-asserts.issues.dir-count
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["training" "strings" "os"])

(def description
  {:en "Implement a function that returns directories count."
   :ru "Создайте функцию, которая подсчитывает количество директорий в массиве."})

(def signature
  {:input [{:argument-name "filepaths" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(words-count [] (gen/generate (gen/choose 2 8)))
          (extension [] (gen/generate (gen/elements [".txt" ".exe" ".mp3" ".docx" ".jpg" ".png" ".pdf" ".sh" ".rb" ".ex" ".erl" ".js" ".html"])))
          (filepath []
            (repeatedly 10 #(str (rand-nth ["C:/" "D:/" "F:/" "/"]) (s/join "/" (faker/words {:lang :en :n (words-count)})) (rand-nth [(extension) ""]))))]
    (gen/tuple (gen/vector (gen/elements (filepath)) 2 15))))

(def test-data
  [{:expected 1
    :arguments [["C:/Projects/something.txt" "file.exe"]]}
   {:expected 0
    :arguments [["brain-games.exe" "gendiff.sh" "task-manager.rb"]]}
   {:expected 3
    :arguments [["C:/Users/JohnDoe/Music/Beethoven_5.mp3" "/usr/bin" "/var/www/myprojectt"]]}])

(defn solution [filepaths]
  (count (filter #(s/includes? % "/") filepaths)))
