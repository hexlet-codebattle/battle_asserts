(ns battle-solutions.key-for-min-value-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn key-for-min-value
  [hsh]
  (first (first (sort-by last hsh))))

(deftest test-asserts
  (assert-equal "j" (key-for-min-value {"k" 2 "h" 3 "j" 1}))
  (assert-equal "z" (key-for-min-value {"o" 0 "z" -2 "j" 1}))
  (assert-equal nil (key-for-min-value {})))
