(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number
  (:require [clojure.test.check.generators :as gen]
            [clojure.string]))

(def level :easy)

(def description "Construct the largest possible number by arranging the integers from the given array.
                 Since the resulting number can be very large and out of int range, you have to represent it as string.
                 For example, from [3, 24, 4] we can construct 6 different numbers: 3244, 3424, 2434, 2443, 4324, 4243
                 and the largest of them is 4324.")

(def signature
  {:input [{:argument-name "numbers" :type {:name "array" :nested {:name "integer"}}}]
   :output {:type {:name "string"}}})

(defn arguments-generator []
  (gen/tuple (gen/not-empty (gen/vector (gen/one-of
                                         [(gen/choose 0 9)
                                          (gen/choose 100 500)
                                          (gen/choose 10 99)])
                                        1
                                        5))))

(def test-data
  [{:arguments [[1 34 3 98 9 76 45 4]]
    :expected "998764543431"}
   {:arguments [[1 2 3 4 5 6]]
    :expected "654321"}
   {:arguments [[481 428 385 202 2 197 106 10]]
    :expected "481428385220219710610"}
   {:arguments [[54 546 548 60]]
    :expected "6054854654"}
   {:arguments [[43 44 12 324 90 9 88 89]]
    :expected "9908988444332412"}
   {:arguments [[1]]
    :expected "1"}
   {:arguments [[1 1]]
    :expected "11"}
   {:arguments [[1 2 223]]
    :expected "22321"}])

(defn my-comparator [val1 val2]
  (compare (Integer. (str val2 val1))
           (Integer. (str val1 val2))))

(defn solution [array]
  (clojure.string/join (sort my-comparator array)))
