(ns battle-asserts.issues.count-and-say
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :hard)

(def description "Conway's \"Look and Say\" sequence is a sequence of numbers in which each term \"reads aloud\" the digits of the previous term.
                 1 is read off as \"one 1\".
                 11 is read off as \"two 1's\".
                 21 is read off as \"one 2, then one 1\".
                 1211 is read off as \"one 1, then one 2, then two 1's\".")

(def signature
  {:input [{:argument-name "str" :type {:name "string"}}]
   :output {:type {:name "string"}}})

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
  (let [numbers {"one" 1, "two" 2, "three" 3}
        repeater (fn [[amount value]]
                   (s/join (repeat (numbers amount) value)))
        strip #(s/replace % #"'s" "")
        split #(s/split % #", then ")
        group #(s/split % #" ")]
    (->>  phrase
          strip
          split
          (map (comp repeater group))
          s/join)))
