(ns battle-asserts.issues.remove-duplicates
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "If a list contains repeated elements they should be replaced with a single copy of the element.
                 The order of the elements should not be changed.")

(defn arguments-generator []
  (letfn [(add-duplicates [coll]
            (concat coll (repeatedly (inc (rand-int 5))
                                     #(rand-nth coll))))]
    (gen/tuple (gen/one-of [(gen/vector gen/int)
                            (gen/bind (gen/vector gen/int 1 8)
                                      #(gen/shuffle (add-duplicates %)))]))))

(def test-data
  [{:expected [1 2 3 4 5]
    :arguments [[1 2 2 2 3 4 4 5]]}
   {:expected [1 2 3]
    :arguments [[1 2 3 2 3]]}
   {:expected [\a \b \c]
    :arguments ["aabcc"]}
   {:expected [1 "foo" 2]
    :arguments [[1 "foo" 2]]}
   {:expected '(a b c d e)
    :arguments ['(a a a a b c c a a d e e e e)]}])

(defn solution [coll]
  (letfn [(contain? [coll value]
            (some #(= % value) coll))]
    (reduce #(if (not (contain? %1 %2))
               (conj %1 %2)
               %1)
            []
            coll)))
