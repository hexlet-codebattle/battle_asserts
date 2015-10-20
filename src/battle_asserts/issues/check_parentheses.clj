(ns battle-asserts.issues.check-parentheses
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Check if the parentheses in the expression are all balanced, so that all open paranthesis is closed properly.")

(defn arguments-generator []
  (let [brackets [\( \)]]
    (letfn [(permutation []
              (apply str (repeatedly 4 #(rand-nth brackets))))]
      (gen/tuple (gen/elements (repeatedly 20 permutation))))))

(def test-data
  [{:expected true
    :arguments ["()"]}
   {:expected true
    :arguments [" ( )(  )"]}
   {:expected true
    :arguments ["(() )"]}
   {:expected false
    :arguments [") "]}
   {:expected false
    :arguments ["("]}
   {:expected false
    :arguments [") ("]}
   {:expected false
    :arguments ["( )  )"]}
   {:expected false
    :arguments ["(( )"]}])

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
