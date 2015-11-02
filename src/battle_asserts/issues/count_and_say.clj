(ns battle-asserts.issues.count-and-say
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :hard)

(def description "Conway's \"Look and Say\" sequence is a sequence of numbers in which each term \"reads aloud\" the digits of the previous term.
                 1 is read off as \"one 1\".
                 11 is read off as \"two 1's\".
                 21 is read off as \"one 2, then one 1\".
                 1211 is read off as \"one 1, then one 2, then two 1's\".")

(defn arguments-generator []
  (letfn [(input []
            (let [combinations ["one %d" "two %d's" "three %d"]]
              (->>
               (range 0 10)
               shuffle
               (take (inc (rand-int 7)))
               (map #(format (rand-nth combinations) %))
               (s/join ", then "))))]
    (gen/tuple (gen/elements (repeatedly 50 input)))))

(def test-data
  [{:expected "111221" :arguments ["three 1, then two 2's, then one 1"]}
   {:expected "11" :arguments ["two 1's"]}
   {:expected "21" :arguments ["one 2, then one 1"]}
   {:expected "1211" :arguments ["one 1, then one 2, then two 1's"]}])

(defn solution [phrase]
  (letfn [(repeater [[amount value]]
            (let [dict {"one" 1, "two" 2, "three" 3}]
              (s/join (repeat (dict amount) value))))]
    (->  phrase
         (s/replace #"'s" "")
         (s/split  #", then ")
         (->> (map #(clojure.string/split % #" "))
              (reduce #(str %1 (repeater %2)) "")))))
