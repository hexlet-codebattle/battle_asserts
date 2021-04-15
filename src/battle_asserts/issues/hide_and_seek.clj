(ns battle-asserts.issues.hide-and-seek
  (:require [clojure.test.check.generators :as gen]
            [faker.name :as fk-name]))

(def level :easy)

(def tags ["collections" "strings"])

(def description "Children are going to play hide and seek.
                  They define the leader by the count in such a way that:
                  One word counting - one person.
                  The last remaining child in the count will be a leader. Counting goes in one direction.
                  The child on whom the count stopped, goes to hide, and the counter
                  continues with the next child after him.
                  The function accepts an array of children's names and the number of words in the counter.
                  Returns the leader's name.")

(def signature
  {:input [{:argument-name "kids" :type {:name "array" :nested {:name "string"}}}
           {:argument-name "counter" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arith-progression [n]
  (if (zero? n)
    0
    (+ n (arith-progression (dec n)))))

(defn arguments-generator []
  (let [kids-number (gen/generate (gen/choose 5 8))
        kids (gen/vector (gen/elements (repeatedly 30 #(fk-name/first-name))) kids-number)
        counter (gen/choose 1 (arith-progression kids-number))]
    (gen/tuple kids counter)))

(def test-data
  [{:expected "Diana" :arguments [["Vova" "Dima" "Diana" "Natali" "Vasya"] 8]}
   {:expected "Natali" :arguments [["Vova" "Dima" "Diana" "Natali" "Vasya"] 9]}
   {:expected "Dima" :arguments [["Vova" "Dima" "Diana" "Natali" "Vasya"] 11]}
   {:expected "Vova" :arguments [["Vova" "Dima" "Diana" "Natali" "Vasya"] 15]}])

(defn proceed-counting [size counter]
  (cond
    (zero? counter) size
    (< counter size) (dec counter)
    :else (proceed-counting (dec size) (- counter size))))

(defn solution [kids counter]
  (let [lead (proceed-counting (count kids) counter)]
    (kids lead)))
