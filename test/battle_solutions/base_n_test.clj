(ns battle-solutions.base-n-test
  (:require [clojure.test :refer :all]
            [battle-asserts.test-helper :refer :all]))

(defn is-base-n
  [number base]
  (-> base
      str
      re-pattern
      (re-find (str number))
      nil?))

(deftest test-asserts
  (assert-equal false (is-base-n 2341 4))
  (assert-equal true (is-base-n 5511 8))
  )
