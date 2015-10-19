(ns battle-asserts.issues.drop-every-n-element
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :elementary)

(def description "Drop every N'th element from a list.")

(defn arguments-generator []
  (gen/tuple (gen/such-that #(not (zero? %)) gen/pos-int)
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
  (keep-indexed #(if-not (= (mod (inc %1) n)
                            0) %2)
                coll))

