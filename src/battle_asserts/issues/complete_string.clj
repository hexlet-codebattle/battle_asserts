(ns battle-asserts.issues.complete-string
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "A string is said to be complete if it contains all the characters from a to z.
                 Given a string, check if it's complete or not.")

(def signature
  {:input [{:argument-name "str" :type {:name "string"}}]
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
  (= (->> s
          (re-seq #"[a-z]")
          distinct
          sort
          s/join)
     "abcdefghijklmnopqrstuvwxyz"))
