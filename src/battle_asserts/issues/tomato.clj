(ns battle-asserts.issues.tomato
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]))

(def level :elementary)

(def tags ["strings" "training"])

(def description
  {:en "Count how many times word `tomato` occurs in string."
   :ru "Подсчитайте сколько раз слово `tomato` встречается в строке."})

(def signature
  {:input  [{:argument-name "vegetables" :type {:name "string"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(tomato-generator []
            (loop [x 20
                   acc (list "tomato")]
              (if (zero? x)
                acc
                (recur (dec x) (conj
                                acc
                                (s/join "" [(first acc) "tomato"]))))))]
    (gen/tuple (gen/elements (tomato-generator)))))

(def test-data
  [{:expected 1
    :arguments ["tomato"]}
   {:expected 3
    :arguments ["tomatotomatotomato"]}])

(defn solution [vegetables]
  (count (s/split vegetables #"(?=tomato)")))
