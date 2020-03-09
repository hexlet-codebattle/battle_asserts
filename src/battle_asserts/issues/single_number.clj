(ns battle-asserts.issues.single-number
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Given an array of integers, every element appears twice except for one. Find that single one.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "integer"}}})

(defn arguments-generator []
  (letfn [(modify [array]
            (let [unique-elements (set array)
                  single-element (last unique-elements)
                  without-last (drop-last unique-elements)]
              (gen/shuffle (apply conj without-last single-element without-last))))]
    (gen/tuple (gen/bind (gen/such-that #(> (count (set %)) 3)
                                        (gen/vector gen/small-integer 3 25))
                         modify))))

(def test-data
  [{:expected 6
    :arguments [[8 6 8 7 2 2 7 1 9 9 1]]}
   {:expected 3
    :arguments [[2 2 3]]}
   {:expected 4
    :arguments [[4 2 2 3 6 3 8 7 8 6 7]]}
   {:expected 5
    :arguments [[1 3 4 3 5 6 7 1 6 7 4]]}])

(defn solution [array]
  (apply bit-xor array))
