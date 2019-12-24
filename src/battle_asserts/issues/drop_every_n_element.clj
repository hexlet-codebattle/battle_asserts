(ns battle-asserts.issues.drop-every-n-element
  (:require [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Drop every N'th element from a list.")

(defn disabled [] true)

(defn signature []
  {:input  [{:argument-name "num" :type {:name "integer"}}
            {:argument-name "list" :type {:name "array" :nested {:name "string"}}}
            ]
   :output {:type {:name "array", :nested {:name "string"}}}})

(defn arguments-generator []
  (gen/tuple gen/s-pos-int
             (gen/vector gen/int)))

(def test-data
  [{:expected ["a" "b" "d" "e" "g" "h" "k"]
    :arguments [3 ["a" "b" "c" "d" "e" "f" "g" "h" "i" "k"]]}
   {:expected [0 2 4 6 8]
    :arguments [2 [0 1 2 3 4 5 6 7 8 9]]}
   {:expected [0 1 2 3]
    :arguments [5 [0 1 2 3]]}
   {:expected []
    :arguments [1 [0 1 2 3 4 5 6 7]]}])

(defn solution [n coll]
  (keep-indexed #(if-not (zero? (mod (inc %1) n)) %2)
                coll))
