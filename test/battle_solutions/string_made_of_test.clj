(ns battle-solutions.string-made-of-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn string-made-of [text]
  (let [m {:1 2 :2 5 :3 5 :4 4 :5 5 :6 6 :7 3 :8 7 :9 6 :0 6}]
    (->> text
         str
         seq
         (map #(m (keyword (str %))))
         (reduce +)
    )))

(deftest test-asserts
  (assert-equal 18 (string-made-of 12134))
  (assert-equal 14 (string-made-of 452))
  (assert-equal 32 (string-made-of 9817567))
  (assert-equal 22 (string-made-of 76814)))
