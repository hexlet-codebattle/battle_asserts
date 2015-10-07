(ns battle-solutions.base-n-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-base-n
  [number base]
  (-> (str "["
           (->> (range base 10)
                (reduce str))
           "]")
      re-pattern
      (re-find (str number))
      nil?))

(deftest test-asserts
  (assert-equal false (is-base-n 6161 3))
  (assert-equal true (is-base-n 1010 2))
  (assert-equal false (is-base-n 2341 4))
  (assert-equal true (is-base-n 5511 8)))
