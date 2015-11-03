(ns battle-asserts.issues.complete-string
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :elementary)

(def description "A string is said to be complete if it contains all the characters from a to z.
                 Given a string, check if it's complete or not.")

(defn arguments-generator []
  (letfn [(alphabet []
            (map char (range (int \a) (inc (int \z)))))
          (incomplete-string []
                             (->>
                              (shuffle (alphabet))
                              (take (inc (rand-int 20)))
                              s/join))
          (generate-string-from [alphabet]
                                (->>
                                 alphabet
                                 cycle
                                 (take (+ 26 (rand-int 5)))
                                 s/join))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 incomplete-string))
                            (gen/elements (repeatedly 50 #(generate-string-from (seq (incomplete-string)))))
                            (gen/elements (repeatedly 50 #(generate-string-from (alphabet))))]))))

(def test-data
  [{:expected false :arguments ["wyyga"]}
   {:expected true :arguments ["qwertyuioplkjhgfdsazxcvbnm"]}
   {:expected false :arguments ["ejuxggfsts"]}
   {:expected true :arguments ["qpwoeirutyalskdjfhgmznxbcv"]}])

(defn solution [s]
  (= (-> s
         seq
         distinct
         sort
         s/join)
     "abcdefghijklmnopqrstuvwxyz"))
