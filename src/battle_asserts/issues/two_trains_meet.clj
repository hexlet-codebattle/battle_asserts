(ns battle-asserts.issues.two-trains-meet
  (:require [clojure.test.check.generators :as gen]
            [battle-asserts.utility :as utility]))

(def level :elementary)

(def description "Compute how long after their deparature two trains will meet.
                 Assume that the trains travel between two points, along a single
                 section of track, going in opposite directions. The function should
                 consume the trains' speeds and the starting distance between the trains.")

(def signature
  {:input [{:argument-name "v1" :type {:name "integer"}}
           {:argument-name "v2" :type {:name "integer"}}
           {:argument-name "distance" :type {:name "integer"}}]
   :output {:type {:name "float"}}})

(defn arguments-generator []
  (gen/fmap #(conj (pop %)
                   (* (apply + (pop %))
                      (peek %)))
            (gen/tuple utility/gen-pos-num
                       utility/gen-pos-num
                       utility/gen-pos-num)))

(def test-data
  [{:expected 1.0 :arguments [50 50 100]}
   {:expected 2.0 :arguments [30 40 140]}
   {:expected 3.0 :arguments [70 50 360]}])

(defn solution
  [v-train1 v-train2 distance]
  (double (/ distance
             (+ v-train1
                v-train2))))
