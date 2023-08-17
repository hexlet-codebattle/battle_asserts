(ns battle-asserts.issues.hide-and-seek
  (:require [clojure.test.check.generators :as gen]
            [faker.name :as fk]))

(def level :easy)

(def disabled true)

(def tags ["collections" "strings"])

(def description
  {:en "Children are going to play hide and seek.
        They define the leader by the count in such a way that:
        One word counting - one person.
        The last remaining child in the count will be a leader. Counting goes in one direction.
        The child on whom the count stopped, goes to hide, and the counter
        continues with the next child after him.
        The function accepts an array of children's names and the number of words in the counter.
        Returns the leader's name."
   :ru "Дети собираются играть в прятки.
        Они определяют водящего по счету следующим образом:
        Один счет слов - один человек.
        Последний оставшийся в подсчете будет водящим. Подсчет идет в одном направлении.
        Ребёнок, на котором остановился счет, прячется, и счет продолжается со следующего по очереди ребенка.
        Функция принимает массив детских имен и количество слов в счетчике.
        Верните имя водящего."})

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
        kids (gen/vector (gen/elements (repeatedly 30 #(fk/first-name))) kids-number)
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
