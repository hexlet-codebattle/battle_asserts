(ns battle-asserts.issues.pair-with-maximum-product
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array with both positive and negative integers,
                 return a pair of integers that, when multiplied, would produce the largest possible number.
                 An element can only be used once, in other words, you cannot multiply a number by itself.
                 Elements of the pair should be arranged in ascending order.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/such-that #(let [sorted (sort %)
                                   first-two (take 2 sorted)
                                   last-two (take-last 2 sorted)]
                               (not= (apply * first-two)
                                     (apply * last-two)))
                            (gen/vector gen/small-integer 2 25))))

(def test-data
  [{:expected [-4 -3]
    :arguments [[-1 -2 -4 -3 0 3 2 1]]}
   {:expected [6 7]
    :arguments [[1 4 3 6 7 0]]}
   {:expected [-5 -4]
    :arguments [[-1 -3 -4 2 0 -5]]}])

(defn product [array]
  (apply * array))

(defn make-pair [first-array second-array]
  (map vector
       first-array
       second-array))

(defn solution [array]
  (->>
   array
   (reduce-kv #(let [next-elements (drop (inc %2) array)]
                 (concat %1 (make-pair (cycle [%3])
                                       next-elements)))
              [])
   (apply max-key product)
   sort))
