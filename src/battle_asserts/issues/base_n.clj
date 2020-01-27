(ns battle-asserts.issues.base-n
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Check whether the number can be a n-nary number (in other words, whether the number is in base-n for n < 10). ")

(def signature
  {:input [{:argument-name "num" :type {:name "integer"}}
           {:argument-name "base" :type {:name "integer"}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (letfn [(number-with-base [base]
            (let [length (inc (rand-int 5))
                  numerals (range 0 base)]
              (Integer/parseInt (s/join (repeatedly length #(rand-nth numerals))))))
          (number-in-other-base [base]
            (let [length1 (inc (rand-int 3))
                  length2 (inc (rand-int 3))
                  numerals-in-system (range 0 base)
                  seq-with-numbers-in-system (repeatedly length1 #(rand-nth numerals-in-system))
                  numerals-out-system (range base 10)
                  seq-with-numbers-out-system (repeatedly length2 #(rand-nth numerals-out-system))]
              (Integer/parseInt (s/join (shuffle (concat seq-with-numbers-in-system seq-with-numbers-out-system))))))
          (rand-element-generator [fun]
            (gen/elements (repeatedly 40 fun)))]
    (gen/bind (gen/choose 1 9)
              #(gen/tuple (gen/one-of [(rand-element-generator (partial number-with-base %))
                                       (rand-element-generator (partial number-in-other-base %))])
                          (gen/return %)))))

(def test-data
  [{:expected false
    :arguments [6161 3]}
   {:expected true
    :arguments [1010 2]}
   {:expected false
    :arguments [2341 4]}
   {:expected true
    :arguments [5511 8]}])

(defn solution [number base]
  (-> (str "["
           (s/join (range base 10))
           "]")
      re-pattern
      (re-find (str number))
      nil?))
