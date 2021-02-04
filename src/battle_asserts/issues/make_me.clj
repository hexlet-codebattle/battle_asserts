(ns battle-asserts.issues.make-me
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def description
  {:en "Create a function that changes specific words into emoticons. Given a sentence as a string, replace the words `smile`, `grin`, `sad`, `shocked`, `bored` and `painful` with their corresponding emoticons."
   :ru "Создайте функцию, которая заменяет настроение (`smile`, `grin`, `sad`, `shocked`, `bored`, `painful`) в конце предложения на один из соответствующих смайликов."})

(def signature
  {:input [{:argument-name "sentence" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(def smile-map
  {"smile" ":D"
   "grin" ":)"
   "sad" ":("
   "shocked" "D:"
   "bored" "(-_-)"
   "painful" "(>_<)"})

(defn arguments-generator []
  (let [sentences (mapv #(str "Make me " (first %)) smile-map)]
    (gen/tuple (gen/elements sentences))))

(def test-data
  [{:expected "Make me :D" :arguments ["Make me smile"]}
   {:expected "Make me :)" :arguments ["Make me grin"]}
   {:expected "Make me :(" :arguments ["Make me sad"]}
   {:expected "Make me D:" :arguments ["Make me shocked"]}
   {:expected "Make me (-_-)" :arguments ["Make me bored"]}
   {:expected "Make me (>_<)" :arguments ["Make me painful"]}])

(defn solution [sentence]
  (let [splitted (s/split sentence #" ")
        emotion (last splitted)
        without-emotion (vec (drop-last splitted))]
    (s/join " " (conj without-emotion (smile-map emotion)))))
