(ns battle-asserts.issues.partiphify
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Create a function that will divide the list by a specified number of parts."
   :ru "Создайте функцию, которая разделит список на конкретное количество частей."})

(def signature
  {:input  [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}
            {:argument-name "parts" :type {:name "integer"}}]
   :output {:type {:name "array" :nested {:name "array" :nested {:name "integer"}}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector gen/small-integer 2 10) (gen/choose 2 4)))

(def test-data
  [{:expected [[1] []] :arguments [[1] 2]}
   {:expected [[1] [2] [3]] :arguments [[1 2 3] 3]}
   {:expected [[1 2 3] [4 5]] :arguments [[1 2 3 4 5] 2]}])

(defn solution [numbers parts]
  (let [part (int (Math/ceil (/ (count numbers) parts)))
        divided-vec (vec (map vec (partition-all part numbers)))
        final-vec (if (not= (count divided-vec) parts) (conj divided-vec []) divided-vec)]
    final-vec))

