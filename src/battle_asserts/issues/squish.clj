(ns battle-asserts.issues.squish
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Write function, which replace any sequences of spaces to single space.")

(defn arguments-generator []
  (letfn [(sentence-with-spaces []
            (s/replace (faker/sentence  {:words-range  [1,10]})
                       #" "
                       (s/join (repeat (inc (rand-int 7))  " "))))]
    (gen/tuple (gen/elements (repeatedly 50 sentence-with-spaces)))))

(def test-data
  [{:expected "This string has too much spaces"
    :arguments ["This    string  has   too    much spaces"]}])

(defn solution [str1]
  (clojure.string/join
   (reverse (reduce
             #(if (and (= (first %1) \space)
                       (= %2 \space))
                %1
                (conj %1 %2))
             '()
             (seq str1)))))
