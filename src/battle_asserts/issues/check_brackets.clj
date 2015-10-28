(ns battle-asserts.issues.check-brackets
  (:require [clojure.test.check.generators :as gen]
            [clojure.string :as s]
            [faker.generate :as faker]))

(def level :medium)

(def description "Check the balance of the brackets in the expression.
                 Brackets can be round: \"()\", square: \"[]\", curly \"{}\" and angle: \"<>\".")

(defn arguments-generator []
  (let [symbols [\( \) \[ \] \{ \} \< \> \space]
        brackets [[\( \)] [\[ \]] [\{ \}] [\< \>]]
        right-combination ["%2$c%1$s%3$c" "%2$c%3$c%1$s" "%2$c %1$s%3$c" "%2$c%3$c %1$s"]]
    (letfn [(permutation []
              (s/join (repeatedly (rand-int 10) #(rand-nth symbols))))
            (shuffled-balanced-permutation []
                                           (s/join (shuffle (seq (balanced-permutation)))))
            (balanced-permutation []
                                  (loop [n (inc (rand-int 10)) result ""]
                                    (if (>= (count result) n)
                                      result
                                      (recur n
                                             (apply format (rand-nth right-combination) result (rand-nth brackets))))))]
      (gen/tuple (gen/one-of [(gen/elements (repeatedly 50 permutation))
                              (gen/elements (repeatedly 50 shuffled-balanced-permutation))
                              (gen/elements (repeatedly 50 balanced-permutation))])))))

(def test-data
  [{:expected false
    :arguments ["( {)  } "]}
   {:expected true
    :arguments ["()[]{}<>"]}
   {:expected true
    :arguments ["<(){[]}>"]}
   {:expected false
    :arguments ["())"]}
   {:expected false
    :arguments ["()("]}
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
