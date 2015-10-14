(ns battle-asserts.issues.domino
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "The input contains the maximal number of dots on one end of a domino bone.
                 Output the number of dots on the domino set.
                 Sample 2 -> 12")

(defn arguments-generator []
  (gen/tuple gen/pos-int))

(defn solution [x]
  (letfn [(rat [x]
            (+ (reduce + (range (+ x 1)))
               (* x (+ x 1))))]
    (->> (range (+ x 1))
         (map #(rat %))
         (reduce +))))
