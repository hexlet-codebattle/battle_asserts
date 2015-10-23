(ns battle-asserts.issues.check-brackets
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "Check the balance of the brackets in the expression.
                 Brackets can be round: \"()\", the square: \"[]\", the curly \"{}\" and angle: \"<>\".")

(defn arguments-generator []
  (let [brackets [\( \) \[ \] \{ \} \< \>]]
    (letfn [(permutation []
              (s/join (repeatedly 4 #(rand-nth brackets))))]
      (gen/tuple (gen/elements (repeatedly 50 permutation))))))

(def test-data
  [{:expected true
    :arguments ["()[]{}<>"]}
   {:expected true
    :arguments ["<(){[]}>"]}
   {:expected false
    :arguments ["())"]}
   {:expected false
    :arguments ["()("]}
   {:expected false
    :arguments ["({)}"]}
   {:expected false
    :arguments ["{)][(}"]}])

(defn solution [s]
  (let [brackets {\( \) \[ \] \{ \} \< \>}
        opening (set (keys brackets))
        closing (set (vals brackets))]
    (->> s
         (reduce #(cond
                    (opening %2) (conj %1 %2)
                    (closing %2) (cond
                                   (= (brackets (last %1)) %2) (pop %1)
                                   :else (reduced (conj %1 %2)))
                    :else %1)
                 [])
         empty?)))
