(ns battle-asserts.issues.vowels-to-numbers
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def tags ["strings"])

(def description
  {:en "In a given word array, replace the vowels in each word with their indexes.
        The indexes are numbered starting from zero."
   :ru "Дан массив со словами. Замените гласные буквы в каждом слове на их индексы.
        Нумерация индексов, конечно, начинается с нуля."})

(def signature
  {:input  [{:argument-name "words" :type {:name "array" :nested {:name "string"}}}]
   :output {:type {:name "array" :nested {:name "string"}}}})

(defn- multiply-vowels [word]
  (let [vowels-count (s/join (repeat (rand-nth (range 3 8)) "$1"))]
    (s/replace word #"([ayuioe])" vowels-count)))

(defn- generate-words []
  (let [to-multiply (random-sample 0.3 (faker/words {:n 30}))
        without-multiply (random-sample 0.8 (faker/words {:n 30}))]
    (concat without-multiply
            (map multiply-vowels to-multiply))))

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/elements (generate-words)) 4 6)))

(def test-data
  [{:expected ["cl2j4r6" "p1th4n" "0l2x4r" "j1v3scr7pt"]
    :arguments [["clojure" "python" "elixir" "javascript"]]}
   {:expected ["s1l3ngw7rd" "w1ws4l6ngw10rd" "w1ws4m6chl10ngw14rd"]
    :arguments [["solongword" "wowsolongword" "wowsomuchlongword"]]}
   {:expected ["wh23456t" "0b234567t" "th2345t?"]
    :arguments [["whaaaaat" "abooouuut" "thaaaat?"]]}])

(defn- replace-vowels [word]
  (let [vowels [\o \u \i \y \e \a]]
    (s/join #"" (map-indexed
                 (fn [index chr] (if (.contains vowels chr) index chr))
                 word))))

(defn solution [words]
  (mapv replace-vowels words))
