(ns battle-asserts.issues.arrange-numbers-to-form-biggest-number
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]))

(def level :easy)

(def description "Given an array of numbers, arrange them in a way that produces the largest value. For example, [1 9 75] -> 9751")

(defn arguments-generator []
  (gen/tuple (gen/not-empty (gen/vector (gen/choose 0 547) 2 8))))

(def test-data
  [{:arguments [[1 2 3 4 5 6]]
    :expected 654321}
   {:arguments [[54 546 548 60]]
    :expected 605485465}
   {:arguments [[1 34 3 98 9 76 45 4]]
    :expected 998764543431}
   {:arguments [[43 44 12 324 90 9 88 89]]
    :expected 9908988444332412}])

(defn my-comparator [val1 val2]
  (compare (Integer. (str val2 val1))
           (Integer. (str val1 val2))))

(defn solution [array]
  (BigInteger. (clojure.string/join (sort my-comparator array))))
