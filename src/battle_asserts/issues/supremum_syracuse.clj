(ns battle-asserts.issues.supremum-syracuse
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "You are given the number s[1], which is the first element of the Syracuse sequence. Find the largest element in the sequence. Syracuse sequence is a sequence given by the formula below, its last element is one. Sequence formula: `s[n]=1`, `s[k+1]=s[k]/2`, if s[k] is even, s[k+1]=(3*s[k]+ 1)/2 if s[k] is odd."
   :ru "Вам дано число s[1], являющееся первым элементом последовательности Сиракузы. Найдите наибольший элемент в последовательности. Последовательности Сиракузы - последовательность заданная идущей ниже формулой, последним элементом которой является единица. Формула последовательности: `s[n]=1`, `s[k+1]=s[k]/2`, если s[k] четно, s[k+1]=(3*s[k]+1)/2, если s[k] нечетно."})

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 1 100)))

(def test-data
  [{:expected 8
    :arguments [6]}
   {:expected 26
    :arguments [11]}
   {:expected 4616
    :arguments [27]}])

(defn solution [num]
  (letfn [(gen-seq
            [num]
            (if (= 1 num)
              [num]
              (concat [num]
                      (if (odd? num)
                        (gen-seq (quot (inc (* 3 num)) 2))
                        (gen-seq (quot num 2))))))]
    (apply max (gen-seq num))))
