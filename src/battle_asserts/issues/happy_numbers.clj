(ns battle-asserts.issues.happy-numbers
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Happy numbers are positive integers that follow a particular formula:
        take each individual digit, square it, and then sum the squares to get a new number.
        Repeat with the new number and eventually, you might get to a number whose squared sum is 1.
        This is a happy number. An unhappy number (or sad number) is one that loops endlessly.
        Write a function that determines if a number is happy or not."
   :ru "Счастливые числа - это положительные целые числа, которые вычисляются следующим образом:
        каждую цифру числа возведите в квадрат, затем сложите получившиеся значение, чтобы получить новое число.
        Повторите процесс с получившимся числом и т.д.  У вас может получится число, сумма квадратов которого равна 1.
        Это счастливое число. Несчастливое (или грустное) число - такое число, которое бесконечно повторяется.
        Напишите функцию которая определяет счастливое число или нет."})

(def signature
  {:input  [{:argument-name "n" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (let [happy-numbers [1 7 10 13 19 23 28 31 32 44 49 68 70 79 82 86 91 94 97 100]]
    (gen/tuple (gen/one-of [(gen/choose 0 1000)
                            (gen/elements happy-numbers)]))))

(def test-data
  [{:expected true
    :arguments [7]}
   {:expected true
    :arguments [986543210]}
   {:expected false
    :arguments [2]}
   {:expected false
    :arguments [189]}])

(defn solution [n]
  (loop [n n seen #{}]
    (cond
      (= n 1)  true
      (seen n) false
      :else
      (recur (->> (str n)
                  (map #(Character/digit % 10))
                  (map #(* % %))
                  (reduce +))
             (conj seen n)))))
