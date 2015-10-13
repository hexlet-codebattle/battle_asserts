(ns battle-solutions.arrange-numbers-to-form-biggest-number-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn my-comparator [val1 val2]
  (compare (Integer. (str val2 val1))
           (Integer. (str val1 val2))))

(defn biggest-number [array]
  (BigInteger. (clojure.string/join (sort my-comparator array))))

(deftest test-asserts
  (assert-equal 654321 (biggest-number [1 2 3 4 5 6]))
  (assert-equal 6054854654 (biggest-number [54 546 548 60]))
  (assert-equal 998764543431 (biggest-number [1 34 3 98 9 76 45 4]))
  (assert-equal 9908988444332412 (biggest-number [43 44 12 324 90 9 88 89])))
