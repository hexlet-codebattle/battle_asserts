(ns battle-asserts.issues.isbn-validator
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :refer [split]]))

(def level :easy)

(def tags ["strings"])

(def description
  {:en "Validate given ISBN identifier.
        An ISBN is a ten digit code which identifies a book.
        The first nine digits represent the book and the last digit is used to make sure the ISBN is correct
        To verify an ISBN you obtain the sum of 10 times the first digit, 9 times the second digit, 8 times the third
        digit ... all the way till you add 1 times the last digit. if the sum leaves no remainder when divided by
        11 the code is valid ISBN."
   :ru "Проверьте на правильность данный ISBN идентификатор.
        ISBN - это десятизначный код, который идентифицирует книгу.
        Первые девять цифр представляют книгу, а последняя используется для проверки корректности.
        Для проверки ISBN нужно просуммировать 10 раз первую цифру, 9 раз вторую, 8 раз третью и так далее,
        в конце нужно прибавить 1 раз последнюю цифру. Если при делении полученной суммы на 11 нет остатка, то ISBN корректен."})

(def signature
  {:input  [{:argument-name "code" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

(defn- special-sum [code]
  (->>
   (range (count code) 0 -1)
   (map * code)
   (apply +)))

(defn- valid-isbn-code []
  (let [nine-first-numers (vec (repeatedly 9 #(rand-int 10)))
        special-sum-without-last (special-sum (conj nine-first-numers 0))
        result (->>
                (mod special-sum-without-last 11)
                (- 11)
                (conj nine-first-numers)
                (apply format "%d-%d%d%d-%d%d%d%d%d-%d"))
        last-num (->>
                  (split result #"-")
                  (last)
                  (Integer/parseInt))]
    (if (>= last-num 10)
      (valid-isbn-code)
      result)))

(defn- invalid-isbn-code []
  (apply format "%d-%d%d%d-%d%d%d%d%d-%d"
         (repeatedly 10 #(rand-int 10))))

(defn arguments-generator []
  (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 valid-isbn-code))
                          (gen/elements (repeatedly 50 invalid-isbn-code))])))

(def test-data
  [{:expected true
    :arguments ["0-684-84328-5"]}
   {:expected true
    :arguments ["960-425-059-0"]}
   {:expected true
    :arguments ["85-359-0277-5"]}
   {:expected true
    :arguments ["0-85131-041-9"]}
   {:expected false
    :arguments ["0-85131-041-8"]}])

(defn- to-integer-array [code]
  (map read-string
       (re-seq #"\d" code)))

(defn solution [code]
  (->
   code
   to-integer-array
   special-sum
   (mod 11)
   zero?))
