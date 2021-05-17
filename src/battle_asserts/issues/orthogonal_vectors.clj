(ns battle-asserts.issues.orthogonal-vectors
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["math"])

(def description
  {:en "Create a function that takes two vectors and checks if them are orthogonal or not.
        Two vectors are orthogonal if their dot product is equal to zero."
   :ru "Напишите функцию, которая принимает два вектора и проверяет ортогональные ли они.
        Два вектора ортогональные если их скалярное произведение равно нулю."})

(def signature
  {:input  [{:argument-name "vec1" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "vec2" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "boolean"}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose -3 3) 2)
             (gen/vector (gen/choose -3 3) 2)))

(def test-data
  [{:expected true :arguments [[1 2] [2 -1]]}
   {:expected true :arguments [[3 -3] [3 3]]}
   {:expected false :arguments [[3 -1] [3 0]]}])

(defn solution [first-vec second-vec]
  (let [zipped (map vector first-vec second-vec)
        dot-product (reduce (fn [acc [first-elem second-elem]] (+ acc (* first-elem second-elem))) 0 zipped)]
    (zero? dot-product)))
