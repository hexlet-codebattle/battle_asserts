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
          (incomplete-string-full-length []
                                         (->>
                                          (seq (incomplete-string))
                                          cycle
                                          (take 26)
                                          s/join))
          (complete-string []
                           (s/join (shuffle (alphabet))))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 incomplete-string))
                            (gen/elements (repeatedly 50 incomplete-string-full-length))
                            (gen/elements (repeatedly 50 complete-string))]))))

(def test-data
  [{:expected false :arguments ["wyyga"]}
   {:expected true :arguments ["qwertyuioplkjhgfdsazxcvbnm"]}
   {:expected false :arguments ["ejuxggfsts"]}
   {:expected true :arguments ["qpwoeirutyalskdjfhgmznxbcv"]}])

(defn solution [s]
  (= (-> s
         seq
         sort
         s/join)
     "abcdefghijklmnopqrstuvwxyz"))
