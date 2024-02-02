(ns battle-asserts.issues.complete-string
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "A text is said to be complete if it contains all the characters from a to z. Given a text, check if it's complete or not."
   :ru "Текст считается полным, если в нем присутствуют все символы от `a` до `z`. Проверьте, является ли переданный текст полным."})

(def signature
  {:input [{:argument-name "text" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(alphabet []
            (map char (range (int \a) (inc (int \z)))))
          (sed-of-numbers-and-letters []
            (concat (alphabet) (range 0 10)))
          (incomplete-string []
            (->>
             (shuffle (alphabet))
             (take (inc (rand-int 20)))
             s/join))
          (generate-string-from [alphabet]
            (->>
             alphabet
             shuffle
             cycle
             (take (+ 26 (rand-int 5)))
             s/join))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 incomplete-string))
                            (gen/elements (repeatedly 50 #(generate-string-from (sed-of-numbers-and-letters))))
                            (gen/elements (repeatedly 50 #(generate-string-from (alphabet))))]))))

(def test-data
  [{:expected false :arguments ["wyyga"]}
   {:expected true :arguments ["qwertyuioplkjhgfdsazxcvbnm"]}
   {:expected false :arguments ["ejuxggfsts"]}
   {:expected true :arguments ["qpwoeirutyalskdjfhgmznxbcv"]}
   {:expected false :arguments ["0123456789abcdefghijklmnop"]}])

(defn solution [s]
  (->> s
       (re-seq #"[a-z]")
       set
       count
       (= 26)))
