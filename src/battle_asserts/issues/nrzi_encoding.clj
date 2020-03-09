(ns battle-asserts.issues.nrzi-encoding
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Non return to zero, inverted (NRZI) is a method of mapping a binary signal to a physical signal
                 for transmission over some transmission media. The two level NRZI signal has a transition at
                 a clock boundary if the bit being transmitted is a logical 1,
                 and does not have a transition if the bit being transmitted is a logical 0.

                 0 100 10000 100 1 1 1
                 ¯|___|¯¯¯¯¯|___|¯|_|¯")

(def signature
  {:input  [{:argument-name "seq" :type {:name "string"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (letfn [(input []
            (let [alphabet ["_" "¯"]]
              (->>
               (repeatedly (+ (rand-int 20) 2) #(rand-nth alphabet))
               (reduce #(if (or (empty? %1)
                                (= (last %1) %2))
                          (conj %1 %2)
                          (conj %1 "|" %2))
                       [])
               s/join)))] (gen/tuple (gen/elements (repeatedly 50 input)))))

(def test-data
  [{:expected "010010000100111"
    :arguments ["¯|___|¯¯¯¯¯|___|¯|_|¯"]}
   {:expected "010110010"
    :arguments ["¯|__|¯|___|¯¯"]}
   {:expected "010011000110"
    :arguments ["_|¯¯¯|_|¯¯¯¯|_|¯¯"]}])

(defn solution [coded-seq]
  (->
   coded-seq
   (s/replace #"\|_|\|¯" "1")
   (s/replace #"[^\d]" "0")))
