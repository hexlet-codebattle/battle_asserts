(ns battle-asserts.issues.binary-search
  (:require [clojure.test.check.generators :as gen]))

(def level :medium)

(def tags ["algorithms" "collections"])

(def description
  {:en "Solve the following challenge:
   Implement a function to find the first occurrence of a specific element in a sorted list using binary search and return its index.
   If the element is not found, return -1."
   :ru "Решите следующее задание:
   Реализуйте функцию для поиска первого вхождения конкретного элемента в упорядоченном списке с использованием бинарного поиска и верните его индекс.
   Если элемент не найден, верните -1."})

(def signature
  {:input [{:argument-name "nums" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "target" :type {:name "integer"}}]
   :output {:type {:name "integer"}}})

(defn generate-elements []
  (vec (sort (distinct (gen/generate (gen/vector (gen/choose 2 121) 10 50))))))

(defn arguments-generator []
  (let [collections (vec (repeatedly 40 generate-elements))]
    (gen/tuple (gen/elements collections) (gen/choose 1 135))))

(def test-data
  [{:expected 1 :arguments [[1 2 2 2 3 4 5] 2]}
   {:expected 0 :arguments [[1 1 1 1 1 1 1] 1]}
   {:expected 4 :arguments [[-10 -5 0 4 7 9 11 11 13 14 15] 7]}
   {:expected -1 :arguments [[1 2 3 4 5] 6]}])

(defn solution [sorted-list target]
  (let [lo 0
        hi (dec (count sorted-list))]
    (loop [lo lo hi hi]
      (if (> lo hi)
        -1
        (let [mid (+ lo (int (/ (- hi lo) 2)))
              mid-val (get sorted-list mid)]
          (cond
            (= mid-val target) (if (or (zero? mid) (not= (get sorted-list (dec mid)) target))
                                 mid
                                 (recur lo (dec mid)))
            (< mid-val target) (recur (inc mid) hi)
            :else (recur lo (dec mid))))))))
