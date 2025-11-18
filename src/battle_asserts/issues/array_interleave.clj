;; Moved to modern repository
(ns battle-asserts.issues.array-interleave
  (:require [clojure.test.check.generators :as gen]))

(def level :easy)

(def tags ["collections"])

(def description
  {:en "Construct a new array by interleaving two input arrays.
        Interleaving is achieved by taking values from each array consecutively:
        1st value from the 1st array, then 1st value from the 2nd array, then 2nd value from the
        1st array, then 2nd value from the 2nd array, etc. Arrays can be of different lengths,
        so if one arrays ends before the other, stop adding new values and return."
   :ru "Создайте новый массив чередованием двух исходных массивов.
        Чередование заключается в последовательном взятии элементов из каждого массива:
        1-й элемент из 1-го массива, потом 1-й элемент из 2-го массива, затем 2-й элемент из
        1-го массива, далее 2-й элемент из 2-го и т.д. Массивы могут иметь разную длину,
        и если элементы в одном закончатся раньше другого, необходимо прекратить добавление и вернуть результат."})

(def signature
  {:input [{:argument-name "arr1" :type {:name "array" :nested {:name "integer"}}}
           {:argument-name "arr2" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "array" :nested {:name "integer"}}}})

(defn arguments-generator []
  (gen/tuple (gen/vector (gen/choose 1 10) 2 5)
             (gen/vector (gen/choose 1 10) 2 5)))

(def test-data
  [{:expected [5 4 1 9]
    :arguments [[5 1 3] [4 9]]}
   {:expected [1 2 3 4 5 6]
    :arguments [[1 3 5] [2 4 6]]}
   {:expected [1 2 3 4]
    :arguments [[1 3 5] [2 4]]}])

(defn solution [array-first array-second]
  (vec (interleave array-first array-second)))
