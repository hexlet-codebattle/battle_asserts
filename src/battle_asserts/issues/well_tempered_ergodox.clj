(ns battle-asserts.issues.well-tempered-ergodox
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["music"])

(def description
  {:en "Write a function that gets a string number and a fret of a 6-string guitar in 'standard tuning' and return the corresponding note. For this challenge we use a 24 fret model. The notes are: `C, C#/Db, D, D#/Eb, E, F, F#/Gb, G, G#/Ab, A, A#/Bb, B`"
   :ru "Напишите функцию, которая получает номер струны и лад 6-струнной гитары в `стандартной настройкe` и возвращает соответствующую ноту. Для этой задачи мы используем модель с 24 ладами. Ноты следующие: `C, C#/Db, D, D#/Eb, E, F, F#/Gb, G, G#/Ab, A, A#/Bb, B`"})

(def signature
  {:input [{:argument-name "str" :type {:name "integer"}}
           {:argument-name "fret" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 6) (gen/choose 0 24)))

(def test-data
  [{:expected "A"
    :arguments [3 2]}
   {:expected "A#"
    :arguments [1 6]}
   {:expected "G"
    :arguments [3 0]}
   {:expected "F#"
    :arguments [2 19]}])

(def notes [:C :C# :D :D# :E :F :F# :G :G# :A :A# :B])

(def tuning [:E :B :G :D :A :E])

(defn string-notes [start]
  (->> notes
       cycle
       (drop-while #(not= start %))
       (take 25)))

(defn solution [string fret]
  (let [open-note (tuning (dec string))]
    (name (nth (string-notes open-note) fret))))
