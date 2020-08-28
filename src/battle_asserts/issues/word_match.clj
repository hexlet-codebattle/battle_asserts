(ns battle-asserts.issues.word-match
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Write a function that takes two string and
checks whether it is possible to write the first string with
characters from the second string, while respecting the order
in which these characters appear in the second string.")

(def signature
  {:input  [{:argument-name "str1" :type {:name "string"}}
            {:argument-name "str2" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(get-string
            [count-sym]
            (let [alphabet [\a \b \c \d \e \f \g \j \k \l]]
              (s/join (repeatedly (inc (rand-int count-sym)) #(rand-nth alphabet)))))
          (get-derived-string
            [str]
            (s/join "" (mapcat #(conj (seq (get-string 3)) %) str)))]
    (gen/one-of [(gen/tuple (gen/elements (repeatedly 30 #(get-string 10)))
                            (gen/elements (repeatedly 30 #(get-string 10))))
                 (gen/bind (gen/elements (repeatedly 30 #(get-string 10)))
                           #(gen/tuple (gen/return %)
                                       (gen/return (get-derived-string %))))])))

(def test-data
  [{:expected  false
    :arguments ["cool" "lomnucoldsea"]}
   {:expected  true
    :arguments ["abba" "ardbulblia"]}])

(defn solution
  [s1 s2]
  (let [result (reduce (fn [[ch & chrs :as s1] v]
                         (if (= ch v)
                           chrs
                           s1)) s1 s2)]
    (zero? (count result))))
