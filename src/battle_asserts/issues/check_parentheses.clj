(ns battle-asserts.issues.check-parentheses
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Checking the parentheses in the expression.")

(defn arguments-generator []
  (let [brackets [\( \)]]
    (letfn [(permutation []
              (apply str (repeatedly 4 #(rand-nth brackets))))]
      (gen/tuple (gen/elements (repeatedly 20 permutation))))))

(defn solution [s]
  (let [opening (set "(")
        closing (set ")")]
    (->> s
         (reduce #(cond
                    (< %1 0) (reduced %1)
                    (opening %2) (inc %1)
                    (closing %2) (dec %1)
                    :else %1)
                 0)
         (= 0))))
