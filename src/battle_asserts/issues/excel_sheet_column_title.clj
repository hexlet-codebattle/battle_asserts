(ns battle-asserts.issues.excel-sheet-column-title
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given a positive integer, return its corresponding column title as it appears in an Excel sheet.
                 For example: 1 -> A, 2 -> B, 3 -> C, ..., 26 -> Z, 27 -> AA, 28 -> AB")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  ;NOTE используем (gen/one-of [(gen/choose ...) ...] для более равномерного распределения значений
  ; (1 .. 27) ~ (A .. AA),  (27 .. 703) ~ (AA .. AAA), (703 .. 30000) ~ (AAA .. ARIV)
  (gen/tuple (gen/one-of [(gen/choose 1 27) (gen/choose 27 703) (gen/choose 703 30000)])))

(def test-data
  [{:expected "YP"
    :arguments [666]}
   {:expected "A"
    :arguments [1]}
   {:expected "Z"
    :arguments [26]}
   {:expected "CPN"
    :arguments [2458]}
   {:expected "AJHX"
    :arguments [24568]}])

(defn solution [num]
  (if (zero? num)
    ""
    (let [n (dec num) A (int \A) size 26]
      (format "%s%s"
              (->
               n
               (quot size)
               solution)
              (->
               n
               (mod size)
               (+ A)
               char)))))
