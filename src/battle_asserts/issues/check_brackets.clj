(ns battle-asserts.issues.check-brackets
  (:require [clojure.string :as s]
            [clojure.test.check.generators :as gen]))

(def level :medium)

(def description "Check the balance of the brackets in the expression.
                 Brackets can be round: \"()\", square: \"[]\", curly \"{}\" and angle: \"<>\".")

(def signature
  {:input [{:argument-name "brackets" :type {:name "string"}}]
   :output {:type {:name "boolean"}}})

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
        opening? (set (keys brackets))
        closing? (set (vals brackets))
        f (fn [stack sym]
            (cond
              (opening? sym) (conj stack sym)
              (closing? sym) (if (= (brackets (last stack)) sym)
                               (pop stack)
                               (reduced (conj stack sym)))
              :else stack))]
    (->> s str (reduce f []) empty?)))
