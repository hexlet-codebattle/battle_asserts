(ns battle-asserts.issues.pythagorean-triple
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Check if 3 given integers form a Pythagorian Triplet.
                 Pythagorian Triplet is a triplet of numbers,
                 such that x^2 + y^2 = z^2")

(def signature
  {:input  [{:argument-name "nums" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn square [i]
  (* i i))

(defn arguments-generator []
  (letfn [(triplet []
            (let [cathetus1 (rand-nth (range 1 100 2))
                  cathetus2 (quot (dec (square cathetus1)) 2)
                  hypotenuse (quot (inc (square cathetus1)) 2)]
              (shuffle [cathetus1 cathetus2 hypotenuse])))]
    (gen/tuple (gen/one-of [(gen/vector (gen/choose 1 120) 3)
                            (gen/elements (repeatedly 50 triplet))]))))

(def test-data
  [{:expected true
    :arguments [[12 5 13]]}
   {:expected true
    :arguments [[3 5 4]]}
   {:expected false
    :arguments [[8 9 7]]}
   {:expected false
    :arguments [[8 3 6]]}])

(defn solution [arr]
  (let [x (->> arr
               sort
               reverse)]
    (= (square (first x))
       (+ (square (first (rest x)))
          (square (last (rest x)))))))
