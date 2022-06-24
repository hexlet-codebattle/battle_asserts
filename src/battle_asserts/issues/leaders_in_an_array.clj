(ns battle-asserts.issues.leaders-in-an-array
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Print all the leaders in the array.
        An element is a leader if it is greater than all the elements to its right side.
        And the rightmost element is always a leader. For example in the array [16 17 4 3 5 2],
        leaders are 17, 5 and 2."
   :ru "Выведите всех лидеров в массиве.
        Элемент является лидером, если он больше всех элементов расположенных правее него.
        Самый правый элемент - всегда лидер. Например в массиве [16 17 4 3 5 2]
        лидерами являются 17, 5 и 2."})

(def signature
  {:input  [{:argument-name "arr" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 1 70) 5 10)))

(def test-data
  [{:expected [17 5 2]
    :arguments [[16 17 4 3 5 2]]}
   {:expected [67 45 35 8]
    :arguments [[4 3 7 12 6 67 5 45 34 35 2 8]]}
   {:expected [12 8 7 6]
    :arguments [[12 10 12 8 7 6]]}
   {:expected [5 4]
    :arguments [[1 2 3 4 5 4]]}])

(defn first-is-biggest [[head & tail]]
  (every? #(> head %) tail))

(defn solution [array]
  (->>
   array
   (map-indexed vector)
   (filter
    #(first-is-biggest (subvec array (first %))))
   (mapv second)))
