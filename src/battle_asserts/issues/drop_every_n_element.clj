(ns battle-asserts.issues.drop-every-n-element
  (:require [battle-asserts.utility :as utility]
            [clojure.test.check.generators :as gen]))

(def level :elementary)

(def description "Drop every N'th element from a list.")

(def signature
  {:input  [{:argument-name "num" :type {:name "integer"}}
            {:argument-name "list" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/choose 2 10)
             (gen/vector (gen/choose 0 10) 5 10)))

(def test-data
  [{:expected [0 2 4 6 8] :arguments [2 [0 1 2 3 4 5 6 7 8 9]]}
   {:expected [0 1 2 3] :arguments [5 [0 1 2 3]]}])

(defn solution [n coll]
  (vec (keep-indexed #(if-not (zero? (mod (inc %1) n)) %2)
                coll)))
