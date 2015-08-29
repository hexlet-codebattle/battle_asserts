(ns battle-solutions.integer-difference-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer [assert-equal]]))

(defn integer-difference
  [differ, nums]
  (reduce +
          (pmap (fn [number]
                  (/
                   (count (filter
                           #(= differ (Math/abs (- % number)))
                           nums))
                   2))
                nums)))

(deftest test-asserts
  (assert-equal 3 (integer-difference 4 [1 1 5 6 9 16 27]))
  (assert-equal 4 (integer-difference 2 [1 1 3 3])))
