(ns battle-solutions.count-and-say-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn count-and-say
  [phrase]
  (letfn [(repeater
            [pair]
            (let [dict {"one" 1, "two" 2, "three" 3}]
              (-> pair
                  (first)
                  (dict)
                  (repeat (last pair))
                  (->> (apply str)))))]
    (->  phrase
         (clojure.string/replace #"'s" "")
         (clojure.string/split   #", then ")
         (->> (map #(clojure.string/split % #" "))
              (reduce #(str %1 (repeater %2)) "")
              (Integer/parseInt)))))

(deftest test-asserts
  (let [input "two 1's"
        output 11]
    (assert-equal output (count-and-say input)))
  (let [input "one 2, then one 1"
        output 21]
    (assert-equal output (count-and-say input)))
  (let [input "one 1, then one 2, then two 1's"
        output 1211]
    (assert-equal output (count-and-say input)))
  (let [input "three 1, then two 2, then one 1"
        output 111221]
    (assert-equal output (count-and-say input))))
