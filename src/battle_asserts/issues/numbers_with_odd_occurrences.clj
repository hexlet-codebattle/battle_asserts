(ns battle-asserts.issues.numbers-with-odd-occurrences
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def description "Find numbers which have odd occurrences in array.")

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (letfn [(vector-with-odd-occurences []
            (let [size (+ 5 (rand-int 10))
                  array (repeatedly size #(rand-int 10))]
              (->>
               (frequencies array)
               (filter #(even? (val %)))
               (take 2)
               keys
               (concat array)
               shuffle)))
          (vector-without-odd-occurences []
            (let [size (+ 5 (rand-int 10))
                  array (repeatedly size #(rand-int 10))]
              (->>
               (frequencies array)
               (filter #(odd? (val %)))
               keys
               (concat array)
               shuffle)))]
    (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 vector-with-odd-occurences))
                            (gen/elements (repeatedly 50 vector-without-odd-occurences))]))))

(def test-data
  [{:expected [34 45] :arguments [[12 23 34 12 12 23 12 45]]}
   {:expected [4 100 5000] :arguments [[4 4 100 5000 4 4 4 4 4 100 100]]}
   {:expected [6 5 9 21] :arguments [[3 3 4 6 4 5 9 9 21 9]]}
   {:expected [8 16 23 42] :arguments [[4 8 15 16 23 42 4 15 42 42]]}])

(defn solution [array]
  (->>
   (frequencies array)
   (filter #(odd? (val %)))
   keys
   (sort-by #(.indexOf array %))
   vec))
