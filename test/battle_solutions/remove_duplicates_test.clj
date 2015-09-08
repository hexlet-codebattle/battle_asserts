(ns battle-solutions.remove-duplicates-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn compress [seq]
  (->> seq
       (reduce #(cond
                  (= %2 (first %1)) %1
                  :else (conj %1 %2))
               nil)
       (reverse)))

(deftest test-asserts
  (assert-equal [] (compress []))
  (assert-equal [1] (compress [1]))
  (assert-equal [\a \b \c] (compress "aabcc"))
  (assert-equal [1 "foo" 2] (compress [1 "foo" 2]))
  (assert-equal [1 2 3 2 3] (compress [1 2 3 2 3]))
  (assert-equal [1 2 3 4 5] (compress [1 2 2 2 3 4 4 5]))
  (assert-equal '(a b c a d e) (compress '(a a a a b c c a a d e e e e))))
